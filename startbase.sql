select * from `stanica`;
call czsmdp.dodajStanicu('Gradiška');
call czsmdp.dodajStanicu('Banja Luka');
call czsmdp.dodajStanicu('Prijedor');
call czsmdp.dodajStanicu('Sanski Most');
call czsmdp.dodajStanicu('Jajce');
call czsmdp.dodajStanicu('Prnjavor');

call czsmdp.dodajKorisnika('admin', 'C7AD44CBAD762A5DA0A452F9E854FDC1E0E7A52A38015F23F3EAB1D80B931DD472634DFAC71CD34EBC35D16AB7FB8A90C81F975113D6C7538DC69DD8DE9077EC', 'admin', 'Gradiška');
call czsmdp.dodajKorisnika('Korisnik2', '5d5023553fe89252bcd1a18558fbaa968748731a9561618f3d474e887b58908c12ef6070c78a7a30d40a131db80b4762eac8737ff34e32de5bbb4a4998e4535f', 'operater', 'Sanski Most');
call czsmdp.dodajKorisnika('Korisnik3', '5ed7f08ae9e68cf7f04c00b1dda8b34e394c75eaa7a203a742e7ba8690b95fe33f4e3cb615b8ea80c94f1a4c26a21febdbc461f74f40b45fb4544d9919081fdf', 'operater', 'Banja Luka');
call czsmdp.dodajKorisnika('Korisnik4', 'a86b9765fd3cf80715a19aed648767502e6025b8fd3f3ccb15728106bb1722d5e94aedd3e971cf3186cbcd329fb98120d8aeb0be5e234554a8541ace55f7faa1', 'operater', 'Prijedor');

call czsmdp.dodajLiniju('Prijedor', 'Jajce');
call czsmdp.dodajStanke('Gradiška', '12:00:00');
call czsmdp.dodajStanke('Banja Luka', '13:00:00');

call czsmdp.vratiLogin('admin');
call sveLinije();