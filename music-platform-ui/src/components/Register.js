import React, { useState } from 'react';
import axios from 'axios';

function Register({ setToken, setUserId, setPage }) {
const [username, setUsername] = useState('');
const [email, setEmail] = useState('');
const [password, setPassword] = useState('');
const [error, setError] = useState('');

const handleRegister = async () => {
try {
const res = await axios.post('http://localhost:8080/api/auth/register', {
username,
email,
password
});
setToken(res.data.token);
setUserId(res.data.userId);
} catch (err) {
setError('Registration failed. Try a different email.');
}
};

return (
<div className="center">
<div className="card" style={{ width: '400px' }}>
<h1 style={{ marginBottom: '8px', fontSize: '28px' }}>🎵 AI Music</h1>
<p style={{ color: '#888', marginBottom: '24px' }}>Create your account</p>

{error && <p style={{ color: '#ff4d4d', marginBottom: '12px' }}>{error}</p>}

<input
type="text"
placeholder="Username"
value={username}
onChange={e => setUsername(e.target.value)}
/>
<input
type="email"
placeholder="Email"
value={email}
onChange={e => setEmail(e.target.value)}
/>
<input
type="password"
placeholder="Password"
value={password}
onChange={e => setPassword(e.target.value)}
/>
<button onClick={handleRegister}>Create Account</button>

<p style={{ textAlign: 'center', marginTop: '16px', color: '#888' }}>
Already have an account?{' '}
<span
onClick={() => setPage('login')}
style={{ color: '#6c63ff', cursor: 'pointer' }}
>
Sign In
</span>
</p>
</div>
</div>
);
}

export default Register;