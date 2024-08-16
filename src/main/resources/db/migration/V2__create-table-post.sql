CREATE TABLE posts (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description text NOT NULL,
    user_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);