ALTER TABLE food_app_user
ADD COLUMN user_id INT NOT NULL,
ADD FOREIGN KEY (user_id)
    REFERENCES application_user (user_id);