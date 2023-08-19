INSERT INTO users (id, create_time, edit_time, email, username, password, i_subscribe, my_subscribers)
VALUES ('a074aa9d-0b51-4769-9225-16498ead172e',
        now(),
        now(),
        'some_data@google.com',
        'BenderTheBest',
        '$2y$12$ysBy/aXV8Ynbzme.cGURLOJoEv9JtOvHBX4bfC3dfK5iVO84Yyf82',
        '{de98d1f9-5184-45fb-9d92-ad2c917576b7}',
        '{de98d1f9-5184-45fb-9d92-ad2c917576b7}'),
       ('de98d1f9-5184-45fb-9d92-ad2c917576b7',
        now(),
        now(),
        'second_data@gmail.com',
        'Mike',
        '$2y$12$ysBy/aXV8Ynbzme.cGURLOJoEv9JtOvHBX4bfC3dfK5iVO84Yyf82',
        '{a074aa9d-0b51-4769-9225-16498ead172e}',
        '{a074aa9d-0b51-4769-9225-16498ead172e}');

INSERT INTO role (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO message (id, create_time, edit_time, title, message, image, user_id)
VALUES ('21da7693-73a9-4ede-a62e-e17359cbaa33',
        now(),
        now(),
        'Greeting!',
        'Test message #1 from Bender.',
        'http\\:test.image',
       'a074aa9d-0b51-4769-9225-16498ead172e'),
       ('3cc425ad-4620-4ad1-8f74-2d881eb4f104',
        now(),
        now(),
        'Greeting too!',
        'Test message #2 from Tyson.',
        'http\\:test1.image',
        'de98d1f9-5184-45fb-9d92-ad2c917576b7');

INSERT INTO users_roles (user_id, roles_id)
VALUES ('a074aa9d-0b51-4769-9225-16498ead172e', 1),
       ('de98d1f9-5184-45fb-9d92-ad2c917576b7', 2);