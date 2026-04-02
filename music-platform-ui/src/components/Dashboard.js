import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Dashboard({ token, userId }) {
const [songs, setSongs] = useState([]);
const [file, setFile] = useState(null);
const [title, setTitle] = useState('');
const [uploading, setUploading] = useState(false);
const [message, setMessage] = useState('');

const fetchSongs = async () => {
try {
const res = await axios.get(`http://localhost:8080/api/songs?userId=${userId}`);
setSongs(res.data);
} catch (err) {
console.error(err);
}
};

useEffect(() => {
fetchSongs();
}, []);

const handleUpload = async () => {
if (!file || !title) return;
setUploading(true);
setMessage('');
const formData = new FormData();
formData.append('file', file);
formData.append('title', title);
formData.append('userId', userId);

try {
await axios.post('http://localhost:8080/api/songs/upload', formData);
setMessage('Upload successful!');
setTitle('');
setFile(null);
fetchSongs();
} catch (err) {
setMessage('Upload failed.');
}
setUploading(false);
};

return (
<div style={{ maxWidth: '800px', margin: '0 auto', padding: '40px 20px' }}>
<h1 style={{ fontSize: '28px', marginBottom: '8px' }}>🎵 Unfamous AI Music Platform 🎵</h1>
<p style={{ color: '#888', marginBottom: '32px' }}>Upload a song and let AI analyze its mood!</p>

<div className="card" style={{ marginBottom: '32px' }}>
<h2 style={{ marginBottom: '16px' }}>Upload a Song</h2>
<input
type="text"
placeholder="Song title"
value={title}
onChange={e => setTitle(e.target.value)}
/>
<input
type="file"
accept="audio/*"
onChange={e => setFile(e.target.files[0])}
style={{ marginTop: '8px' }}
/>
<button onClick={handleUpload} disabled={uploading}>
{uploading ? 'Analyzing...' : 'Upload & Analyze'}
</button>
{message && <p style={{ marginTop: '12px', color: '#6c63ff' }}>{message}</p>}
</div>

<div className="card">
<h2 style={{ marginBottom: '16px' }}>Your Songs</h2>
{songs.length === 0 ? (
<p style={{ color: '#888' }}>No songs uploaded yet.</p>
) : (
songs.map(song => (
<div key={song.id} style={{
padding: '16px',
marginBottom: '12px',
background: '#111',
borderRadius: '8px',
borderLeft: '4px solid #6c63ff'
}}>
<h3 style={{ marginBottom: '4px' }}>{song.title}</h3>
<p style={{ color: '#aaa', fontSize: '14px' }}>
Mood: <span style={{ color: '#6c63ff' }}>{song.mood || 'Analyzing...'}</span>
</p>
<p style={{ color: '#aaa', fontSize: '14px' }}>
Tempo: {song.tempo ? `${song.tempo.toFixed(1)} BPM` : 'N/A'} |
Energy: {song.energy ? song.energy.toFixed(4) : 'N/A'}
</p>
</div>
))
)}
</div>
</div>
);
}

export default Dashboard;