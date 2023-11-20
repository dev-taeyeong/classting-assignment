INSERT IGNORE INTO administrators (id, name)
VALUES (1, '관리자1'),
       (2, '관리자2'),
       (3, '관리자3'),
       (4, '관리자4'),
       (5, '관리자5');

INSERT IGNORE INTO school_pages (id, administrator_id, location, name)
VALUES (1, 1, '강남', '강남초등학교'),
       (2, 2, '강북', '강북초등학교'),
       (3, 3, '천호', '천호중학교'),
       (4, 4, '부산', '부산초등학교'),
       (5, 5, '대구', '대구중학교');
