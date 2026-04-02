import React, { useState } from 'react';
import axios from 'axios';

function Login({ setToken, setUserId, setPage }) {
const [email, setEmail] = useState('');
const [password, setPassword] = useState('');
const [error, setError] = useState('');

const handleLogin = async () => {
try {
const res = await axios.post('http://localhost:8080/api/auth/login', {
email,
password
});
console.log(res.data);
setToken(res.data.token);
setUserId(res.data.userId);
} catch (err) {
setError('Invalid email or password');
}
};

return (
<div className="center">
<div className="card" style={{ width: '400px' }}>
<h1 style={{ marginBottom: '8px', fontSize: '28px' }}>🎵Unfamous AI Music</h1>
<p style={{ color: '#888', marginBottom: '24px' }}>Sign in to your account</p>

{error && <p style={{ color: '#ff4d4d', marginBottom: '12px' }}>{error}</p>}

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
<button onClick={handleLogin}>Sign In</button>

<p style={{ textAlign: 'center', marginTop: '16px', color: '#888' }}>
Don't have an account?{' '}
<span
onClick={() => setPage('register')}
style={{ color: '#6c63ff', cursor: 'pointer' }}
>
Register
</span>
</p>
</div>
</div>
);
}

export default Login;