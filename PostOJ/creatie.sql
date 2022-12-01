/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  MM
 * Created: 1-dec-2022
 */
drop table Pakket;
drop table Adres;
drop table Werknemers;


create table Werknemers(
    wid int primary key,
    functie varchar(20)
);

create table Adres(
    aid int primary key,
    naam varchar(20),
    straatennr varchar(20),
    postcode int,
    gemeente varchar(20)
);

create table Pakket(
    pid int primary key,
    status varchar(20),
    datum varchar(20),
    tijd varchar(20),
    commentaar varchar(20),
    paid int  references Adres,
    pwid int  references Werknemers
);