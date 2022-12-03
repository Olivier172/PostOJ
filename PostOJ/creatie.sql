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
    wnaam varchar(40),
    functie varchar(40)
);

create table Adres(
    aid int primary key,
    naam varchar(100),
    straatennr varchar(100),
    postcode int,
    gemeente varchar(100)
);

create table Pakket(
    pid int primary key,
    status varchar(20),
    datum date,
    tijd time,
    gewicht int,
    commentaar varchar(500),
    paid int  references Adres,
    pwid int  references Werknemers
);