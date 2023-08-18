INSERT INTO users (create_time, edit_time, email, username, password, i_subscribe, my_subscribers)
VALUES ( now(),
         now(),
         'some_data@google.com',
         'BenderTheBest',
         '$2y$12$ysBy/aXV8Ynbzme.cGURLOJoEv9JtOvHBX4bfC3dfK5iVO84Yyf82',
         '{2}',
         '{2}'),
       (
           now(),
           now(),
           'second_data@gmail.com',
           'Mike',
           '$2y$12$ysBy/aXV8Ynbzme.cGURLOJoEv9JtOvHBX4bfC3dfK5iVO84Yyf82',
           '{1}',
           '{1}');

INSERT INTO role (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO message (create_time, edit_time, title, message, image, user_id)
VALUES (now(),
        now(),
        'Greeting!',
        'Test message #1 from Bender.',
        'http\\:test.image',
        1),
       (now(),
        now(),
        'Greeting too!',
        'Test message #2 from Tyson.',
        'http\\:test1.image',
        2);

INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 1),
       (2, 2);