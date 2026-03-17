-- Database initialization script for TDD application

-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS tdd_db;

-- Connect to tdd_db and create initial schema
\c tdd_db;

-- Create Task table
CREATE TABLE IF NOT EXISTS task (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    completed BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create index for better query performance
CREATE INDEX idx_task_completed ON task(completed);

-- Display confirmation
SELECT 'TDD Database initialized successfully' as status;
