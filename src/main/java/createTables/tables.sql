create table if not exists person(
                                     id int primary key ,
                                     username varchar(255) ,
                                     password varchar(255)
);

create table if not exists twitter(
                                      id int primary key ,
                                      id_person int ,
                                      content varchar(280) ,
                                      constraint pk_idP foreign key (id_person) references person(id)
);

create table if not exists comment(
                                      id int primary key ,
                                      id_person int ,
                                      id_twitter int ,
                                      content varchar(280) ,
                                      constraint pk_idP foreign key (id_person) references person(id) ,
                                      constraint pk_idT foreign key (id_twitter) references twitter(id)
)