drop database hotel_alura;
create database hotel_alura;
use hotel_alura;


create table tbAdministrator (
	ADMIN_ID				smallint auto_increment not null,
	USERNAME				varchar(32) not null,
	PASSWORD				varchar(128) not null,
	
	primary key (ADMIN_ID)
);

insert into tbAdministrator (USERNAME, PASSWORD)
	values ('admin', 'admin');


create table tbGuest (
	GUEST_ID				int auto_increment not null,
	FIRST_NAME				varchar(64) not null,
	LAST_NAME				varchar(64) not null,
	BIRTHDAY				date not null,
	NATIONALITY				varchar(32) not null,
	PHONE					varchar(24) not null,
	
	primary key (GUEST_ID)
);


create table tbBooking (
	BOOKING_ID				int auto_increment not null,
	BOOKING_NUMBER			varchar(16) not null,
	CHECKIN_DATE			date not null,
	CHECKOUT_DATE			date not null,
	PRICE					double not null,
	PAYMENT_METHOD			varchar(32) not null,
	GUEST_ID				int not null,
	
	primary key (BOOKING_ID),
	foreign key (GUEST_ID) references tbGuest(GUEST_ID)
);
