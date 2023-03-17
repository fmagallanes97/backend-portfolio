INSERT INTO resume (first_name, last_name, headline, about)
VALUES
    ('John', 'Doe', 'Web Developer', 'I am a web developer with 5 years of experience in building web applications.'),
    ('Jane', 'Doe', 'UI/UX Designer', 'I am a UI/UX designer with 7 years of experience designing user interfaces for mobile and web applications.'),
    ('Bob', 'Smith', 'Software Engineer', 'I am a software engineer with experience in developing software applications using Java and Python.');

INSERT INTO skill_type (name)
VALUES
    ('Programming'),
    ('Design'),
    ('Database');

INSERT INTO skill (resume_id, type_id, name)
VALUES
    (1, 1, 'JavaScript'),
    (1, 1, 'CSS'),
    (1, 2, 'Adobe Photoshop'),
    (2, 2, 'Adobe XD'),
    (2, 2, 'Sketch'),
    (2, 1, 'HTML'),
    (3, 1, 'Java'),
    (3, 1, 'Python'),
    (3, 3, 'MySQL');

INSERT INTO contact (email, github_profile_url, linkedin_profile_url, resume_id)
VALUES
    ('john.doe@email.com', 'https://github.com/johndoe', 'https://www.linkedin.com/in/johndoe/', 1),
    ('jane.doe@email.com', 'https://github.com/janedoe', 'https://www.linkedin.com/in/janedoe/', 2),
    ('bob.smith@email.com', 'https://github.com/bobsmith', 'https://www.linkedin.com/in/bobsmith/', 3);

INSERT INTO education (degree, school, academic_field, start_date, end_date, resume_id)
VALUES
    ('Bachelor of Science in Computer Science', 'ABC University', 'Computer Science', '2014-09-01', '2018-06-30', 1),
    ('Master of Fine Arts in Graphic Design', 'XYZ School of Art and Design', 'Graphic Design', '2010-09-01', '2012-06-30', 2),
    ('Bachelor of Science in Electrical Engineering', 'University of PQR', 'Electrical Engineering', '2016-09-01', '2020-06-30', 3);


INSERT INTO position (role, company_name, start_date, end_date, resume_id)
VALUES
    ('Software Engineer', 'ABC Inc.', '2021-01-01', NULL, 1),
    ('Data Analyst', 'XYZ Corp.', '2019-05-01', '2021-12-31', 1),
    ('Product Manager', 'PQR Corp.', '2017-02-15', '2020-11-30', 1),
    ('Web Developer', 'EFG Ltd.', '2020-06-01', '2022-03-31', 2),
    ('UI/UX Designer', 'LMN Corp.', '2018-09-01', '2021-06-30', 2),
    ('Frontend Developer', 'STU Inc.', '2019-11-01', '2022-08-31', 2),
    ('Database Administrator', 'VWX Corp.', '2016-04-15', '2021-01-31', 3),
    ('Software Engineer', 'GHI Ltd.', '2017-09-01', '2020-12-31', 3),
    ('Mobile Application Developer', 'OPQ Corp.', '2018-03-01', '2021-12-31', 3);

INSERT INTO position_responsibility (description, position_id)
VALUES
    ('Develop and maintain web applications using HTML, CSS, and JavaScript.', 1),
    ('Implement new features and optimize existing code for performance and scalability.', 1),
    ('Analyze and interpret data to provide insights and recommendations for business decisions.', 2),
    ('Create and maintain data pipelines to ensure data accuracy and consistency.', 2),
    ('Lead cross-functional teams to deliver product releases on time and within budget.', 3),
    ('Gather and prioritize product requirements based on customer needs and market trends.', 3),
    ('Collaborate with cross-functional teams to design user interfaces for web and mobile applications.', 4),
    ('Create wireframes, mockups, and prototypes to communicate design ideas and user flows.', 4),
    ('Develop and maintain backend systems using Python and Django framework.', 5),
    ('Collaborate with frontend developers to integrate backend systems with user interfaces.', 5),
    ('Implement and manage cloud infrastructure using AWS and Terraform.', 6),
    ('Monitor and optimize system performance, security, and cost.', 6),
    ('Design and implement software applications using Java and Spring framework.', 7),
    ('Collaborate with cross-functional teams to ensure software applications meet business requirements.', 7),
    ('Administer and optimize relational databases using SQL and Oracle.', 8),
    ('Develop and maintain RESTful APIs to interface with frontend systems.', 8),
    ('Develop and maintain mobile applications using React Native and TypeScript.', 9),
    ('Collaborate with product managers and designers to ensure mobile applications meet user needs and design standards.', 9);

INSERT INTO project (title, description, start_date, website, repository_url, preview_image_url, resume_id)
VALUES
    ('E-commerce website', 'Developed a fully functional e-commerce website using React and Node.js', '2022-01-01', 'https://example.com', 'https://github.com/example/e-commerce', 'https://example.com/image.png', 1),
    ('Mobile app', 'Designed and developed a mobile application for tracking daily expenses', '2021-05-01', NULL, 'https://github.com/example/expenses-tracker', NULL, 2),
    ('Web application', 'Developed a web application using Django framework for managing employee data', '2020-02-01', NULL, NULL, NULL, 3);
