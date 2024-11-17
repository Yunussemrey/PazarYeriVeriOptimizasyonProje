// src/components/ThemeToggle/ThemeToggle.js
import React from 'react';
import './ThemeToggle.css';

function ThemeToggle({ toggleTheme }) {
  return (
    <button className="theme-toggle-btn" onClick={toggleTheme}>
      Gece/Gündüz Modu
    </button>
  );
}

export default ThemeToggle;
