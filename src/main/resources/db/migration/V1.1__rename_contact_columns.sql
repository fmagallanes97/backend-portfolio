alter table contact
    change github github_profile_url varchar(255) null;

alter table contact
    change linkedin linkedin_profile_url varchar(255) null;
