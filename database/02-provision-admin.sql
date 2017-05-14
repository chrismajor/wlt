-- script for elevating an existing user to admin level
select id into @user_id from wlt.user where username = 'admin@chrismajor.io';
select id into @role_id from wlt.role where name = 'role_admin';
insert into wlt.user_role (user_id, role_id) values (@user_id, @role_id);
