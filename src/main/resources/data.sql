insert into clients (created_by, created_when, full_name, phone)
values ('initial script', now(), 'Василий Шишкин', '+79654741234'),
       ('initial script', now(), 'Емельянова Анна Викторовна', '+79384098403'),
       ('initial script', now(), 'Львов Марк Вячеславович', '+79169729443'),
       ('initial script', now(), 'Маркина Дарья Давидовна', '+79112213553'),
       ('initial script', now(), 'Васильева Вероника Николаевна', '+79117070026'),
       ('initial script', now(), 'Золотова София Александровна', '+79329270668'),
       ('initial script', now(), 'Тихонова Карина Владимировна', '+79612314093'),
       ('initial script', now(), 'Родина Дарья Давидовна', '+79082837874'),
       ('initial script', now(), 'Егоров Артём Даниилович', '+79605234417'),
       ('initial script', now(), 'Семенов Демид Максимович', '+79286184564'),
       ('initial script', now(), 'Алексеева София Фёдоровна', '+79014914962'),
       ('initial script', now(), 'Елизаров Дмитрий Владиславович', '+79974500996'),
       ('initial script', now(), 'Андреева Дарья Данииловна', '+79946649763'),
       ('initial script', now(), 'Зубкова Полина Алексеевна', '+79868451631'),
       ('initial script', now(), 'Кулагин Матвей Лукич ', '+79176435507'),
       ('initial script', now(), 'Фадеева Александра Захаровна', '+79336362968'),
       ('initial script', now(), 'Васильев Леонид Борисович', '+79542822651'),
       ('initial script', now(), 'Евдокимов Алексей Матвеевич', '+79999060271'),
       ('initial script', now(), 'Корнеев Матвей Даниилович', '+79774258602'),
       ('initial script', now(), 'Воробьев Гордей Андреевич', '+79102122402'),
       ('initial script', now(), 'Судаков Александр Михайлович', '+79416496633');

insert into employees_positions(title, description, role)
values ('Ювелир', 'Рядовой сотрудник, может добавлять заказы и клиентов, смотреть список заказов', 'USER'),
       ('Управляющий', 'Управляющий, может просматривать список сотрудников, добавлять их и удалять', 'MANAGER'),
       ('Директор', 'Директор может все', 'DIRECTOR');

insert into employees (created_by, created_when, login, password, full_name, phone, address,
                       birth_date, position_id)
values ('initial script', now(), 'mahala', 'CHANGE_ME', 'Александрова Майя Викторовна', '+79652393630',
        'Брянская область, город Люберцы, шоссе Славы, 42', '1978-01-23', 1),
       ('initial script', now(), 'jori', 'CHANGE_ME', 'Матвеева Виолетта Даниловна', '+79662129010',
        'Курганская область, город Истра, ул. Гоголя, 09', '1963-08-24', 1),
       ('initial script', now(), 'mateo', 'CHANGE_ME', 'Фомин Егор Богданович', '+71456775126',
        'Омская область, город Видное, въезд Славы, 58',
        '1956-12-22', 1),
       ('initial script', now(), 'stanislaus', 'CHANGE_ME', 'Олейников Михаил Эмильевич', '+73503622352',
        'Читинская область, город Серпухов, пл. Бухарестская, 39', '1981-07-26', 1),
       ('initial script', now(), 'meta', 'CHANGE_ME', 'Беляева Ульяна Ильинична', '+78478556253',
        'Псковская область, город Можайск, наб. Косиора, 80',
        '1966-10-07', 1),
       ('initial script', now(), 'latrena', 'CHANGE_ME', 'Попова Алиса Мироновна', '+73688514741',
        'Калужская область, город Дорохово, пр. Косиора, 49', '1998-11-15', 1),
       ('initial script', now(), 'jakie', 'CHANGE_ME', 'Меркулова Арина Максимовна', '+79089385747',
        'Иркутская область, город Можайск, ул. Ладыгина, 50',
        '1964-09-09', 1),
       ('initial script', now(), 'lil', 'CHANGE_ME', 'Короткова Анна Даниловна', '+79661969201',
        'Орловская область, город Люберцы, пер. Гагарина, 72',
        '1974-01-12', 1),
       ('initial script', now(), 'griz', 'CHANGE_ME', 'Горелов Матвей Егорович', '+74203343412',
        'Ульяновская область, город Талдом, шоссе Ленина, 05',
        '1977-03-20', 1),
       ('initial script', now(), 'ansell', 'CHANGE_ME', 'Агапова Мадина Даниэльевна', '+73123281376',
        'Ростовская область, город Балашиха, спуск Славы, 11',
        '1981-07-20', 1),
       ('initial script', now(), 'catriona', 'CHANGE_ME', 'Егоров Мирон Иванович', '+79309081631',
        'Костромская область, город Серебряные Пруды, пр. Бухарестская, 85', '1985-05-19', 1),
       ('initial script', now(), 'louise', 'CHANGE_ME', 'Громова Ярослава Давидовна', '+79546567989',
        'Ивановская область, город Солнечногорск, въезд Гоголя, 98',
        '1974-10-21', 1),
       ('initial script', now(), 'evita', 'CHANGE_ME', 'Иванова Надежда Максимовна', '+78301645294',
        'Новосибирская область, город Луховицы, пр. Космонавтов, 20', '1969-11-11', 1),
       ('initial script', now(), 'roze', 'CHANGE_ME', 'Кузнецов Кирилл Даниилович', '+74474869003',
        'Челябинская область, город Раменское, пр. Гоголя, 05',
        '1992-03-19', 1),
       ('initial script', now(), 'hymie', 'CHANGE_ME', 'Данилова Алина Никитична', '+76617885395',
        'Орловская область, город Кашира, въезд Бухарестская, 92', '1986-09-02', 1),
       ('initial script', now(), 'selestina', 'CHANGE_ME', 'Волков Андрей Давидович', '+71536252540',
        'Амурская область, город Волоколамск, проезд Гоголя, 60', '1990-05-19', 1),
       ('initial script', now(), 'arel', 'CHANGE_ME', 'Журавлева Арина Ивановна', '+79402087181',
        'Курская область, город Озёры, наб. Гоголя, 37',
        '1991-08-17', 2),
       ('initial script', now(), 'iago', 'CHANGE_ME', 'Чернов Давид Львович', '+71201705579',
        'Архангельская область, город Зарайск, наб. Сталина, 58',
        '1982-12-20', 2),
       ('initial script', now(), 'goraud', 'CHANGE_ME', 'Давыдова София Юрьевна', '+72068010274',
        'Рязанская область, город Пушкино, проезд Гагарина, 03', '1950-07-16', 2),
       ('initial script', now(), 'vladimir', 'CHANGE_ME', 'Добромилов Владимир Пересветович', '+77770000000',
        'Орловская область, город Зарайск, пл. Сталина, 45', '1952-10-07', 3);

insert into gems_types(created_by, created_when, title)
values ('initial script', now(), 'Бриллиант'),
       ('initial script', now(), 'Рубин'),
       ('initial script', now(), 'Александрит'),
       ('initial script', now(), 'Изумруд'),
       ('initial script', now(), 'Сапфир'),
       ('initial script', now(), 'Муассанит'),
       ('initial script', now(), 'Топаз'),
       ('initial script', now(), 'Танзанит'),
       ('initial script', now(), 'Опал'),
       ('initial script', now(), 'Фианит');
;

insert into metals_types(created_by, created_when, title)
values ('initial script', now(), 'Красное золото 585'),
       ('initial script', now(), 'Красное золото 750'),
       ('initial script', now(), 'Белое золото 585'),
       ('initial script', now(), 'Белое золото 750'),
       ('initial script', now(), 'Желтое золото 585'),
       ('initial script', now(), 'Желтое золото 750'),
       ('initial script', now(), 'Золото другой пробы'),
       ('initial script', now(), 'Серебро 925'),
       ('initial script', now(), 'Серебро другой пробы'),
       ('initial script', now(), 'Иной металл');

insert into finished_items(created_by, created_when, item_code, description, metals_info, gems_info)
values ('initial script', now(), 2301, 'Обручальное кольцо, размер 15', 'Белое золото 750, 3,0г',
        'Бриллиант 3мм, 0.11кт'),
       ('initial script', now(), 2301, 'Обручальное кольцо, размер 15.5', 'Белое золото 750, 3,3г',
        'Бриллиант 3мм, 0.11кт'),
       ('initial script', now(), 2301, 'Обручальное кольцо, размер 16', 'Белое золото 750, 3,5г',
        'Бриллиант 3мм, 0.11кт'),
       ('initial script', now(), 2301, 'Обручальное кольцо, размер 16.5', 'Белое золото 750, 3,7г',
        'Бриллиант 3мм, 0.11кт'),
       ('initial script', now(), 2301, 'Обручальное кольцо, размер 17', 'Белое золото 750, 3,9г',
        'Бриллиант 3мм, 0.11кт'),
       ('initial script', now(), 2301, 'Обручальное кольцо, размер 17.5', 'Белое золото 750, 4,1г',
        'Бриллиант 3мм, 0.11кт'),
       ('initial script', now(), 2301, 'Обручальное кольцо, размер 18', 'Белое золото 750, 4,3г',
        'Бриллиант 3мм, 0.11кт'),
       ('initial script', now(), 2301, 'Обручальное кольцо, размер 18.5', 'Белое золото 750, 4,5г',
        'Бриллиант 3мм, 0.11кт'),
       ('initial script', now(), 2302, 'Серьги с бриллиантами', 'Белое золото 750, 3,6г',
        '2шт - Бриллиант 3.8мм, 0.2кт'),
       ('initial script', now(), 2303, 'Классическое обручальное кольцо, размер 18', 'Красное золото 585, 4.3г', null),
       ('initial script', now(), 2303, 'Классическое обручальное кольцо, размер 18.5', 'Красное золото 585, 4.6г',
        null),
       ('initial script', now(), 2303, 'Классическое обручальное кольцо, размер 19', 'Красное золото 585, 4.9г', null),
       ('initial script', now(), 2303, 'Классическое обручальное кольцо, размер 19.5', 'Красное золото 585, 5.2г',
        null),
       ('initial script', now(), 2303, 'Классическое обручальное кольцо, размер 20', 'Красное золото 585, 5.5г', null),
       ('initial script', now(), 2303, 'Классическое обручальное кольцо, размер 20.5', 'Красное золото 585, 5.8г',
        null),
       ('initial script', now(), 2303, 'Классическое обручальное кольцо, размер 21', 'Красное золото 585, 6.3г', null),
       ('initial script', now(), 2303, 'Классическое обручальное кольцо, размер 21.5', 'Красное золото 585, 6.6г',
        null),
       ('initial script', now(), 2303, 'Классическое обручальное кольцо, размер 22', 'Красное золото 585, 6.9г', null),
       ('initial script', now(), 2304, 'Браслет гвоздь', 'Желтое золото 750, 28г', null),
       ('initial script', now(), 2305, 'Кольцо-змея, размер 19', 'Серебро 925, 4.5г', null),
       ('initial script', now(), 2306, 'Браслет с карбоновыми вставками', 'Белое золото 585, 24г',
        '2шт - Бриллиант 3мм, 0.15кт'),
       ('initial script', now(), 2307, 'Серьги в форме бобов', 'Красное золото 585, 5,2г', null);

insert into finished_items_gems_types(finished_item_id, gem_type_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 10),
       (22, 5);

insert into finished_items_metals_types(finished_item_id, metal_type_id)
values (1, 4),
       (2, 4),
       (3, 4),
       (4, 4),
       (5, 4),
       (6, 4),
       (7, 4),
       (8, 4),
       (9, 4),
       (10, 1),
       (11, 1),
       (12, 1),
       (13, 1),
       (14, 1),
       (15, 1),
       (16, 1),
       (17, 1),
       (18, 1),
       (19, 6),
       (20, 8),
       (21, 3),
       (22, 2);

insert into clients_orders(created_by, created_when, description, deadline, price, employee_id, client_id, is_completed,
                           completed_when)
values ('initial script', now(), 'Изготовление цепи из красного золота 585пробы, длиной 60см', '2023-06-13', 40000, 1,
        1, true, '2023-06-19'),
       ('initial script', now(), 'Пайка золотой цепи, общий вес 26гр', '2023-06-13', 1000, 1, 1, true, '2023-06-15'),
       ('initial script', now(), 'Увеличение кольца до 18,5 размера', '2023-06-13', 2000, 1, 2, true, '2023-06-15'),
       ('initial script', now(), 'Гравировка на кольце "Мой бобрик"', '2023-06-13', 1000, 1, 3, true, '2023-06-13'),
       ('initial script', now(), 'Изготовление двух обручальных колец по эскизу, размеры: 16,5 и 19', '2023-06-14',
        20000, 2, 2, true, '2023-06-15'),
       ('initial script', now(), 'Полировка часов Rolex Daytona', '2023-06-14', 15000, 2, 3, true, '2023-06-15'),
       ('initial script', now(), 'Полировка часов Breitling', '2023-06-14', 10000, 2, 4, true, '2023-06-15'),
       ('initial script', now(), 'Закрепка выпавших камней', '2023-06-15', 4000, 2, 4, true, '2023-06-15'),
       ('initial script', now(), 'Полировка и родирование кольца', '2023-06-15', 3500, 3, 6, true, '2023-06-16'),
       ('initial script', now(), 'Изготовление серег по эскизу', '2023-06-16', 20000, 3, 9, true, '2023-06-19'),
       ('initial script', now(), 'Полировка часов Frederique Constant', '2023-06-16', 8000, 4, 7, true, '2023-06-18'),
       ('initial script', now(), 'Пайка цепи', '2023-06-16', 1000, 4, 8, true, '2023-06-19'),
       ('initial script', now(), 'Изготовление браслета по эскизу', '2023-06-17', 3000, 5, 12, false, null),
       ('initial script', now(), 'Замена пружины замка', '2023-06-17', 500, 6, 14, true, '2023-06-17'),
       ('initial script', now(), 'Изготовление двух колец по эскизу', '2023-06-19', 3500, 6, 1, false, null);