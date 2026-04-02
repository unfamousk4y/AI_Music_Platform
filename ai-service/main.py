from fastapi import FastAPI, File, UploadFile
import librosa
import numpy as np
import tempfile
import os

app = FastAPI()

@app.get("/health")
def health():
	return {"status": "ok"}

@app.post("/predict")
async def predict(file: UploadFile = File(...)):
	with tempfile.NamedTemporaryFile(delete=False, suffix=".mp3") as tmp:
		contents = await file.read()
		tmp.write(contents)
		tmp_path = tmp.name

		try:
			y, sr = librosa.load(tmp_path, duration=30)
			tempo, _ = librosa.beat.beat_track(y=y, sr=sr)
			energy = float(np.mean(librosa.feature.rms(y=y)))
			spectral_centroid = float(np.mean(librosa.feature.spectral_centroid(y=y, sr=sr)))
			if tempo > 120 and energy > 0.05:
				mood = "sounds hype!"
			elif tempo < 90 and energy < 0.02:
				mood = "sounds pretty chill.."
			else:
				mood = "honestly, sounds neutral."

			return {
				"mood": mood,
				"tempo": float(tempo),
				"energy": energy,
				"spectral_centroid": spectral_centroid,
				"confidence": 0.85
			}
		finally:
			os.unlink(tmp_path)
