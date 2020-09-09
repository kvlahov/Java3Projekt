create table userProfile (
	userprofileid int primary key generated always as identity,
	username text not null,
	passwordhash text not null
);

create table category (
	categoryid int primary key generated always as identity,
	categoryname text not null,
	description text
);

create table item (
	itemid int primary key generated always as identity,
	itemname text not null,
	itemdescription text,
	price real not null,
	availableunits int not null,
	categoryid int references category(categoryid) not null
);

create table orderreceipt (
	orderreceiptid int primary key generated always as identity,
	datetime timestamp,
	userprofileid int references userprofile(userprofileid) not null
);

create table orderitem (
	orderitemid int primary key generated always as identity,
	itemid int references item(itemid) not null,
	priceperunit real not null,
	quantity int not null,
	total real not null,
	orderreceiptid int references orderreceipt(orderreceiptid) not null
);
