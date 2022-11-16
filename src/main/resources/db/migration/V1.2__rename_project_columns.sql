alter table project
    change github_repository repository_url varchar(255) null;

alter table project
    change preview_image preview_image_url varchar(255) null;
