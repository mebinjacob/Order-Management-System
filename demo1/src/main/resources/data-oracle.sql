insert into ROLE values(1, 'ADMIN');
--inventory manager
insert into ROLE values(2, 'IVMGR'); 
insert into IMGMT_USER values(1, 'Mebin', null,'Jacob', null, 'admin', 1, null, null, null, null, 'mebinjacob');
insert into IMGMT_USER values(1, 'Mebin1', null,'Jacob1', null, 'IVMGR', 1, null, null, null, null, 'mebinjacob');
update  imgmt_user set email='mebinjacob1' where passwd='IVMGR';
update  imgmt_user set role_id=2 where passwd='IVMGR';
commit;