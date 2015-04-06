insert into ROLE values(1, 'ADMIN');
--inventory manager
insert into ROLE values(2, 'IVMGR');
insert into ROLE values(3, 'BRMGR');
insert into ROLE values(4, 'STMGR');

insert into IMGMT_USER values(1, 'Mebin', null,'Jacob', null, 'admin', 1, null, null, null, null, 'mebinjacob');
insert into IMGMT_USER values(2, 'Mebin1', null,'Jacob1', null, 'IVMGR', 1, null, null, null, null, 'mebinjacob');
insert into IMGMT_USER values(3, 'Mebin', null,'Jacob', null, 'IVMGR', 3, null, null, null, null, 'mebinjacob2');
insert into IMGMT_USER values(3, 'Mebin', null,'Jacob', null, 'IVMGR', 3, null, null, null, null, 'mebinjacob3');

update  imgmt_user set email='mebinjacob1' where passwd='IVMGR';
update  imgmt_user set role_id=2 where passwd='IVMGR';
alter table item_order add  item_id integer;
alter table item_order add constraint item_fk foreign key (item_id) references ITEM(item_id);
commit;
