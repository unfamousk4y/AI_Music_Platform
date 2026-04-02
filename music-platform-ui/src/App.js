import React, { useState } from 'react';
import Login from './components/Login';
import Register from './components/Register';
import Dashboard from './components/Dashboard';

function App() {
const [token, setToken] = useState(null);
const [userId, setUserId] = useState(null);
const [page, setPage] = useState('login');

if (token) {
return <Dashboard token={token} userId={userId} />;
}

return (
<div>
{page === 'login' ? (
<Login setToken={setToken} setUserId={setUserId} setPage={setPage} />
) : (
<Register setToken={setToken} setUserId={setUserId} setPage={setPage} />
)}
</div>
);
}

export default App;