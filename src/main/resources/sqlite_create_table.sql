drop table if exists Geocache;
drop table if exists Log;
drop table if exists LogImage;
drop table if exists Waypoint;
drop table if exists Trackable;
drop table if exists TrackableDetail;
drop table if exists Distance;

create table Geocache (
	code varchar not null, 
	cacheId varchar, 
	description varchar, 
	foundCount integer, 
	difficulty float, 
	favoritePoint integer, 
	hint varchar, 
	lastVisited date, 
	latitude float, 
	latlonDM varchar, 
	longitude float, 
	name varchar, 
	owner varchar, 
	placeDate date, 
	premium integer, 
	size varchar, 
	terrain float, 
	type varchar, 
	uri varchar, 
	primary key (code)
);
create table Log (
	logID bigint not null, 
	accountGuid varchar, 
	accountID bigint, 
	avatarImage varchar, 
	cacheID bigint, 
	challengesCompleted integer, 
	created date, 
	creator varchar, 
	email varchar, 
	geocacheFindCount integer, 
	geocacheHideCount integer, 
	isEncoded integer, 
	latLonString varchar, 
	latitude float, 
	logGuid varchar, 
	logText varchar, 
	logType varchar, 
	logTypeImage varchar, 
	longitude float, 
	membershipLevel integer, 
	userName varchar, 
	visited date, 
	gccode varchar, 
	primary key (logID)
);
create table LogImage (
	imageID bigint not null,
	cacheID bigint, 
	created date, 
	descr varchar, 
	fileName varchar, 
	imageGuid varchar, 
	imageUrl varchar, 
	logID bigint, 
	name varchar, 
	primary key (imageID)
);
create table Waypoint (
	id integer primary key autoincrement, 
	coords varchar, 
	lookup varchar, 
	name varchar, 
	note varchar, 
	prefix varchar, 
	type varchar, 
	gccode varchar
);
create table Trackable (
	guid varchar not null,
	name varchar,
	inCache varchar, 
	primary key (guid)
);
create table TrackableDetail (
	guid varchar not null,
	owner varchar,
	releaseDate date,
	origin varchar,
	coordInfoCode varchar,
	goal varchar, 
	primary key (guid)
);
create table Distance(
	code varchar not null,
	distance float,
	primary key (code)
);