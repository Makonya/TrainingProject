insert into locale(LOCALE_NAME) values('ru');
insert into locale(LOCALE_NAME) values('en');

insert into category(ID_CATEGORY, ID_LOCALE, CATEGORY_NAME) values(1, 1, 'Математика');
insert into category(ID_CATEGORY, ID_LOCALE, CATEGORY_NAME) values(1, 2, 'Mathematics');

insert into course(COURSE_NAME, DESCRIPTION, START_DATE, END_DATE, ID_USER, ID_CATEGORY)
			      values('programming', 'this course very helpfull', '2019-01-01', '2019-02-28', 4, 1);