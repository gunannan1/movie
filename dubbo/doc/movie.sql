-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  banner_address VARCHAR(50) COMMENT 'banner图存放路径',
  banner_url VARCHAR(200) COMMENT 'banner点击跳转url',
  is_valid INT DEFAULT 0 COMMENT '是否弃用 0-失效,1-失效'
) COMMENT '电影图标信息表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO banner(banner_address,banner_url) VALUES('banners/9d75708ae91d5fc918369b76e9e2edba197666.jpg','www.meetingshop.cn');
INSERT INTO banner(banner_address,banner_url) VALUES('banners/15b3730838f35d56a76d88a1787aaaa5186414.jpg','www.meetingshop.cn');
INSERT INTO banner(banner_address,banner_url) VALUES('banners/51afa73f0347e9b98957c53fa971d41491652.jpg','www.meetingshop.cn');
INSERT INTO banner(banner_address,banner_url) VALUES('banners/6605d3518e2bba10f29a6f9ae32b361987015.jpg','www.meetingshop.cn');
INSERT INTO banner(banner_address,banner_url) VALUES('banners/c1a713981cabef02c88ae5f42888de70183835.jpg','www.meetingshop.cn');



-- ----------------------------
-- Table structure for category_dict
-- ----------------------------
DROP TABLE IF EXISTS `category_dict`;
CREATE TABLE `category_dict`(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  show_name VARCHAR(100) COMMENT '显示名称'
) COMMENT '电影类型信息表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of category_dict
-- ----------------------------
INSERT INTO category_dict(id,show_name) values(99,'全部');
INSERT INTO category_dict(id,show_name) values(1,'爱情');
INSERT INTO category_dict(id,show_name) values(2,'喜剧');
INSERT INTO category_dict(id,show_name) values(3,'动画');
INSERT INTO category_dict(id,show_name) values(4,'剧情');
INSERT INTO category_dict(id,show_name) values(5,'恐怖');
INSERT INTO category_dict(id,show_name) values(6,'惊悚');
INSERT INTO category_dict(id,show_name) values(7,'科幻');
INSERT INTO category_dict(id,show_name) values(8,'动作');
INSERT INTO category_dict(id,show_name) values(9,'悬疑');
INSERT INTO category_dict(id,show_name) values(10,'犯罪');
INSERT INTO category_dict(id,show_name) values(11,'冒险');
INSERT INTO category_dict(id,show_name) values(12,'战争');
INSERT INTO category_dict(id,show_name) values(13,'奇幻');
INSERT INTO category_dict(id,show_name) values(14,'运动');
INSERT INTO category_dict(id,show_name) values(15,'家庭');
INSERT INTO category_dict(id,show_name) values(16,'古装');
INSERT INTO category_dict(id,show_name) values(17,'武侠');
INSERT INTO category_dict(id,show_name) values(18,'西部');
INSERT INTO category_dict(id,show_name) values(19,'历史');
INSERT INTO category_dict(id,show_name) values(20,'传记');
INSERT INTO category_dict(id,show_name) values(21,'歌舞');
INSERT INTO category_dict(id,show_name) values(22,'短片');
INSERT INTO category_dict(id,show_name) values(23,'纪录片');
INSERT INTO category_dict(id,show_name) values(24,'黑色电影');





-- ----------------------------
-- Table structure for source_dict
-- ----------------------------
DROP TABLE IF EXISTS source_dict;
CREATE TABLE source_dict(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  show_name VARCHAR(100) COMMENT '显示名称'
) COMMENT '电影区域信息表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of mooc_source_dict_t
-- ----------------------------
INSERT INTO source_dict(id,show_name) values(99,'全部');
INSERT INTO source_dict(id,show_name) values(1,'大陆');
INSERT INTO source_dict(id,show_name) values(2,'美国');
INSERT INTO source_dict(id,show_name) values(3,'韩国');
INSERT INTO source_dict(id,show_name) values(4,'日本');
INSERT INTO source_dict(id,show_name) values(5,'中国香港');
INSERT INTO source_dict(id,show_name) values(6,'中国台湾');
INSERT INTO source_dict(id,show_name) values(7,'印度');
INSERT INTO source_dict(id,show_name) values(8,'法国');
INSERT INTO source_dict(id,show_name) values(9,'英国');
INSERT INTO source_dict(id,show_name) values(10,'俄罗斯');
INSERT INTO source_dict(id,show_name) values(11,'意大利');
INSERT INTO source_dict(id,show_name) values(12,'西班牙');
INSERT INTO source_dict(id,show_name) values(13,'德国');
INSERT INTO source_dict(id,show_name) values(14,'波兰');
INSERT INTO source_dict(id,show_name) values(15,'澳大利亚');
INSERT INTO source_dict(id,show_name) values(16,'伊朗');


-- ----------------------------
-- Table structure for year_dict
-- ----------------------------
DROP TABLE IF EXISTS `year_dict`;
CREATE TABLE year_dict(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  show_name VARCHAR(100) COMMENT '显示名称'
) COMMENT '电影年代信息表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of mooc_year_dict_t
-- ----------------------------
INSERT INTO year_dict(id,show_name) values(99,'全部');
INSERT INTO year_dict(id,show_name) values(1,'更早');
INSERT INTO year_dict(id,show_name) values(2,'70年代');
INSERT INTO year_dict(id,show_name) values(3,'80年代');
INSERT INTO year_dict(id,show_name) values(4,'90年代');
INSERT INTO year_dict(id,show_name) values(5,'2000-2010');
INSERT INTO year_dict(id,show_name) values(6,'2011');
INSERT INTO year_dict(id,show_name) values(7,'2012');
INSERT INTO year_dict(id,show_name) values(8,'2013');
INSERT INTO year_dict(id,show_name) values(9,'2014');
INSERT INTO year_dict(id,show_name) values(10,'2015');
INSERT INTO year_dict(id,show_name) values(11,'2016');
INSERT INTO year_dict(id,show_name) values(12,'2017');
INSERT INTO year_dict(id,show_name) values(13,'2018');
INSERT INTO year_dict(id,show_name) values(14,'2018以后');



-- ----------------------------
-- Table structure for film
-- ----------------------------
DROP TABLE IF EXISTS `film`;
CREATE TABLE film(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  film_name VARCHAR(100) COMMENT '影片名称',
  film_type INT COMMENT '片源类型: 0-2D,1-3D,2-3DIMAX,4-无',
  img_address VARCHAR(200) COMMENT '影片主图地址',
  film_score VARCHAR(20) COMMENT '影片评分',
  film_preSaleNum INT COMMENT '影片预售数量',
  film_box_office INT COMMENT '影片票房：每日更新，以万为单位',
  film_source INT COMMENT '影片片源，参照片源字典表',
  film_cats VARCHAR(50) COMMENT '影片分类，参照分类表,多个分类以#分割',
  film_area INT COMMENT '影片区域，参照区域表',
  film_date INT COMMENT '影片上映年代，参照年代表',
  film_time TIMESTAMP COMMENT '影片上映时间',
  film_status INT COMMENT '影片状态,1-正在热映，2-即将上映，3-经典影片'
) COMMENT '影片主表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of film
-- ----------------------------
INSERT INTO film(id,film_name,film_source,film_type,film_cats,film_area,film_date,film_time,film_preSaleNum,film_box_office,film_score,film_status,img_address)
			values(2,'我不是药神',1,0,'#2#4#22#',1,13,'2018-07-05',231432491,309600,'9.7',1,'films/238e2dc36beae55a71cabfc14069fe78236351.jpg');



-- ----------------------------
-- Table structure for film_info
-- ----------------------------
DROP TABLE IF EXISTS `film_info`;
CREATE TABLE film_info(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  film_id VARCHAR(100) COMMENT '影片编号',
  film_en_name VARCHAR(50) COMMENT '影片英文名称',
  film_score VARCHAR(20) COMMENT '影片评分',
  film_score_num INT COMMENT '评分人数,以万为单位',
  film_length INT COMMENT '播放时长，以分钟为单位，不足取整',
  biography TEXT COMMENT '影片介绍',
  director_id INT COMMENT '导演编号',
  film_imgs TEXT COMMENT '影片图片集地址,多个图片以逗号分隔'
) COMMENT '影片信息表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of film_info
-- ----------------------------
INSERT INTO film_info(film_id,film_en_name,film_score,film_score_num,film_length,director_id,film_imgs,biography)
	values(2,'Dying To Survive','9.7',225,117,1,
		'films/3065271341357040f5f5dd988550951e586199.jpg,films/6b2b3fd6260ac37e5ad44d00ea474ea3651419.jpg,films/4633dd44c51ff15fc7e939679d7cdb67561602.jpg,films/df2d30b1a3bd58fb1d38b978662ae844648169.jpg,films/c845f6b04aa49059951fd55e6b0eddac454036.jpg',
		'一位不速之客的意外到访，打破了神油店老板程勇（徐峥 饰）的平凡人生，他从一个交不起房租的男性保健品商贩，一跃成为印度仿制药“格列宁”的独家代理商。收获巨额利润的他，生活剧烈变化，被病患们冠以“药神”的称号。但是，一场关于救赎的拉锯战也在波涛暗涌中慢慢展开......'
	);


-- ----------------------------
-- Table structure for actor
-- ----------------------------
DROP TABLE IF EXISTS `actor`;
CREATE TABLE actor(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  actor_name VARCHAR(50) COMMENT '演员名称',
  actor_img  VARCHAR(200) COMMENT '演员图片位置'
) COMMENT '演员表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of actor
-- ----------------------------
INSERT INTO actor(id,actor_name,actor_img) value(1,'徐峥','actors/2b98c9d2e6d23a7eff25dcac8b584b0136045.jpg');
INSERT INTO actor(id,actor_name,actor_img) value(2,'王传君','actors/b782d497577baffb5ed14de52841dcb164365.jpg');
INSERT INTO actor(id,actor_name,actor_img) value(3,'谭卓','actors/acf7db57456cb1aed1a42f7ebffedaa842002.jpg');
INSERT INTO actor(id,actor_name,actor_img) value(4,'黄渤','actors/c6594ef2705dcaf7d9df857d228b5e1645712.jpg');
INSERT INTO actor(id,actor_name,actor_img) value(5,'舒淇','actors/6b32a489467283bb739a2bac3b2b929742175.jpg');
INSERT INTO actor(id,actor_name,actor_img) value(6,'张艺兴','actors/b738d5e78a1f5c3379d9d42a9b18286f32246.jpeg');
INSERT INTO actor(id,actor_name,actor_img) value(7,'强森','actors/7e3067d066c1e285b0cc17bfd5f1b34e108474.jpg');
INSERT INTO actor(id,actor_name,actor_img) value(8,'杰森·斯坦森','actors/7ec0c90aec03c7904c1db3af1153162f77864.jpg');
INSERT INTO actor(id,actor_name,actor_img) value(9,'李冰冰','actors/d2258cd0529950cf5099206519d91d0e51803.jpg');
INSERT INTO actor(id,actor_name,actor_img) value(10,'汤姆·克鲁斯','actors/6afaea1cb4ca2b346e86e265347c78b833970.jpg');

-- ----------------------------
-- Table structure for film_actor
-- ----------------------------
DROP TABLE IF EXISTS `film_actor`;
CREATE TABLE film_actor(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  film_id INT COMMENT '影片编号,对应mooc_film_t',
  actor_id INT COMMENT '演员编号,对应mooc_actor_t',
  role_name VARCHAR(100) COMMENT '角色名称'
) COMMENT '影片与演员映射表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of film_actor
-- ----------------------------
INSERT INTO film_actor(id,film_id,actor_id,role_name)
	values(1,2,1,'演员1');
INSERT INTO film_actor(id,film_id,actor_id,role_name)
	values(2,2,2,'演员2');
INSERT INTO film_actor(id,film_id,actor_id,role_name)
	values(3,2,3,'演员3');
INSERT INTO film_actor(id,film_id,actor_id,role_name)
	values(4,2,4,'演员4');

-- ----------------------------
-- Table structure for user
-- ----------------------------

DROP TABLE IF EXISTS `user`;
CREATE TABLE user(
   id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
   user_name VARCHAR(50) COMMENT '用户账号',
   user_pwd VARCHAR(50) COMMENT '用户密码',
   nick_name VARCHAR(50) COMMENT '用户昵称',
   user_sex INT COMMENT '用户性别 0-男，1-女',
   birthday VARCHAR(50) COMMENT '出生日期',
   email VARCHAR(50) COMMENT '用户邮箱',
   user_phone VARCHAR(50) COMMENT '用户手机号',
   address VARCHAR(50) COMMENT '用户住址',
   head_url VARCHAR(50) COMMENT '头像URL',
   biography VARCHAR(200) COMMENT '个人介绍',
   life_state INT COMMENT '生活状态 0-单身，1-热恋中，2-已婚，3-为人父母',
   begin_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   UNIQUE KEY `user_name` (`user_name`) USING BTREE

) COMMENT '用户表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

insert into user(user_name,user_pwd,nick_name,user_sex,birthday,email,user_phone,address,head_url,life_state,biography) values('admin','0192023a7bbd73250516f069df18b500','隔壁泰山',0,'2018-07-31','admin@mooc.com','13888888888','北京市海淀区朝阳北路中南海国宾馆','films/img/head-img.jpg',0,'没有合适的伞，我宁可淋着雨');
insert into user(user_name,user_pwd,nick_name,user_sex,birthday,email,user_phone,address,head_url,life_state,biography) values('jiangzh','5e2de6bd1c9b50f6e27d4e55da43b917','阿里郎',0,'2018-08-20','jiangzh@mooc.com','13866666666','北京市朝阳区大望路万达广场','films/img/head-img.jpg',1,'我喜欢隔壁泰山');


-- ----------------------------
-- Table structure for brand_dict
-- ----------------------------
DROP TABLE IF EXISTS `brand_dict`;
CREATE TABLE brand_dict(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  show_name VARCHAR(100) COMMENT '显示名称'
) COMMENT '影城品牌字典表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of brand_dict
-- ----------------------------
INSERT INTO brand_dict(id,show_name) VALUES(99,'全部');
INSERT INTO brand_dict(id,show_name) VALUES(1,'大地影院');
INSERT INTO brand_dict(id,show_name) VALUES(2,'万达影城');
INSERT INTO brand_dict(id,show_name) VALUES(3,'耀莱成龙国际影城');
INSERT INTO brand_dict(id,show_name) VALUES(4,'保利国际影城');
INSERT INTO brand_dict(id,show_name) VALUES(5,'博纳国际影城');
INSERT INTO brand_dict(id,show_name) VALUES(6,'金逸影城');
INSERT INTO brand_dict(id,show_name) VALUES(7,'中影国际影城');
INSERT INTO brand_dict(id,show_name) VALUES(8,'CGV影城');
INSERT INTO brand_dict(id,show_name) VALUES(9,'橙天嘉禾影城');
INSERT INTO brand_dict(id,show_name) VALUES(10,'新华国际影城');
INSERT INTO brand_dict(id,show_name) VALUES(11,'星美国际影城');
INSERT INTO brand_dict(id,show_name) VALUES(12,'百老汇影城');
INSERT INTO brand_dict(id,show_name) VALUES(13,'UME国际影城');
INSERT INTO brand_dict(id,show_name) VALUES(14,'幸福蓝海国际影城');
INSERT INTO brand_dict(id,show_name) VALUES(15,'首都电影院');
INSERT INTO brand_dict(id,show_name) VALUES(16,'华谊兄弟影院');
INSERT INTO brand_dict(id,show_name) VALUES(17,'卢米埃影城');
INSERT INTO brand_dict(id,show_name) VALUES(18,'沃美影城');
INSERT INTO brand_dict(id,show_name) VALUES(19,'美嘉欢乐影城');
INSERT INTO brand_dict(id,show_name) VALUES(20,'嘉华国际影城');
INSERT INTO brand_dict(id,show_name) VALUES(21,'17.5影城');
INSERT INTO brand_dict(id,show_name) VALUES(22,'太平洋电影城');
INSERT INTO brand_dict(id,show_name) VALUES(23,'SFC上影影城');
INSERT INTO brand_dict(id,show_name) VALUES(24,'嘉美国际影城');
INSERT INTO brand_dict(id,show_name) VALUES(25,'东都影城');
INSERT INTO brand_dict(id,show_name) VALUES(26,'鲁信影城');
INSERT INTO brand_dict(id,show_name) VALUES(27,'华影国际影城');
INSERT INTO brand_dict(id,show_name) VALUES(28,'搜秀影城');
INSERT INTO brand_dict(id,show_name) VALUES(29,'横店电影城');



-- ----------------------------
-- Table structure for hall_dict
-- ----------------------------
DROP TABLE IF EXISTS `hall_dict`;
CREATE TABLE hall_dict(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  show_name VARCHAR(100) COMMENT '显示名称',
  seat_address VARCHAR(200) COMMENT '座位文件存放地址'
) COMMENT '影厅种类字典表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of hall_dict
-- ----------------------------
INSERT INTO hall_dict(id,show_name) VALUES(99,'全部');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(1,'IMAX厅','seats/123214.json');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(2,'CGS中国巨幕厅','seats/123214.json');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(3,'杜比全景声厅','seats/123214.json');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(4,'Dolby Cinema厅','seats/123214.json');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(5,'RealD厅','seats/123214.json');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(6,'RealD 6FL厅','seats/123214.json');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(7,'LUXE巨幕厅','seats/123214.json');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(8,'4DX厅','seats/123214.json');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(9,'DTS:X 临境音厅','seats/123214.json');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(10,'儿童厅','seats/123214.json');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(11,'4K厅','seats/123214.json');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(12,'4D厅','seats/123214.json');
INSERT INTO hall_dict(id,show_name,seat_address) VALUES(13,'巨幕厅','seats/123214.json');



-- ----------------------------
-- Table structure for cinema
-- ----------------------------
DROP TABLE IF EXISTS `cinema`;
CREATE TABLE cinema(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  cinema_name VARCHAR(50) COMMENT '影院名称',
  cinema_phone VARCHAR(50) COMMENT '影院电话',
  brand_id INT COMMENT '品牌编号',
  area_id INT COMMENT '地域编号',
  hall_ids VARCHAR(200) COMMENT '包含的影厅类型,以#作为分割',
  img_address VARCHAR(500) COMMENT '影院图片地址',
  cinema_address VARCHAR(200) COMMENT '影院地址',
  minimum_price INT DEFAULT 0 COMMENT '最低票价'
) COMMENT '影院信息表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of cinema
-- ----------------------------
INSERT INTO cinema(id,cinema_name,brand_id,area_id,cinema_address,minimum_price,img_address,cinema_phone,hall_ids)
	VALUES(1,'大地影院(顺义店)',1,1,"北京市顺义区华联金街购物中心",60,'/cinemas/30445282__5675168.jpg','18500003333','#1#3#5#6#');
INSERT INTO cinema(id,cinema_name,brand_id,area_id,cinema_address,minimum_price,img_address,cinema_phone,hall_ids)
	VALUES(2,'大地影院(中关村店)',1,2,"北京市中关村海龙大厦",60,'/cinemas/30445282__5675168.jpg','010-58391939','#1#2#3#4#');
INSERT INTO cinema(id,cinema_name,brand_id,area_id,cinema_address,minimum_price,img_address,cinema_phone,hall_ids)
	VALUES(3,'万达影院(大屯店)',2,3,"北京市朝阳区大屯路50号金街商场",60,'/cinemas/44374823__5777386.jpg','010-58391939','#5#6#7#8#');
INSERT INTO cinema(id,cinema_name,brand_id,area_id,cinema_address,minimum_price,img_address,cinema_phone,hall_ids)
	VALUES(4,'万达影院(奥体中心店)',2,4,"北京市朝阳区奥林匹克公园新奥购物中心",60,'/cinemas/44374823__5777386.jpg','010-58391231','#1#3#5#6#');
INSERT INTO cinema(id,cinema_name,brand_id,area_id,cinema_address,minimum_price,img_address,cinema_phone,hall_ids)
	VALUES(5,'万达影院(中南海店)',2,5,"北京市东城区中南海52号",60,'/cinemas/44374823__5777386.jpg','010-58398521','#1#5#7#8#');
INSERT INTO cinema(id,cinema_name,brand_id,area_id,cinema_address,minimum_price,img_address,cinema_phone,hall_ids)
	VALUES(6,'万达影院(国贸店)',2,6,"北京市朝阳区国贸CBD核心商场5012",60,'/cinemas/5_0805163047.jpg','010-96385274','#1#2#3#7#');
INSERT INTO cinema(id,cinema_name,brand_id,area_id,cinema_address,minimum_price,img_address,cinema_phone,hall_ids)
	VALUES(7,'慕课影院(大屯店)',3,7,"北京市朝阳区大屯路50号金街商场",60,'/cinemas/5_0805163047.jpg','010-98765432','#1#5#8#9#');


-- ----------------------------
-- Table structure for screening
-- ----------------------------
DROP TABLE IF EXISTS `screening`;
CREATE TABLE screening(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  cinema_id INT COMMENT '影院编号',
  film_id INT COMMENT '电影编号',
  begin_time VARCHAR(50) COMMENT '开始时间',
  end_time VARCHAR(50) COMMENT '结束时间',
  hall_id INT COMMENT '放映厅类型编号',
  hall_name VARCHAR(200) COMMENT '放映厅名称',
  price INT COMMENT '票价'
) COMMENT '放映场次表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of screening
-- ----------------------------
INSERT INTO screening(id,cinema_id,film_id,begin_time,end_time,hall_id,hall_name,price)
	VALUES(1,1,2,'09:50','11:20',1,'一号厅',60);
INSERT INTO screening(id,cinema_id,film_id,begin_time,end_time,hall_id,hall_name,price)
	VALUES(2,1,3,'11:50','13:20',2,'IMAX厅',60);
INSERT INTO screening(id,cinema_id,film_id,begin_time,end_time,hall_id,hall_name,price)
	VALUES(3,1,3,'13:50','15:20',3,'飞翔体验厅',60);
INSERT INTO screening(id,cinema_id,film_id,begin_time,end_time,hall_id,hall_name,price)
	VALUES(4,3,2,'11:50','13:20',3,'7号超大厅',60);
INSERT INTO screening(id,cinema_id,film_id,begin_time,end_time,hall_id,hall_name,price)
	VALUES(5,3,2,'11:50','13:20',4,'飞翔体验厅',60);
INSERT INTO screening(id,cinema_id,film_id,begin_time,end_time,hall_id,hall_name,price)
	VALUES(6,5,2,'11:50','13:20',5,'3号VIP厅',60);
INSERT INTO screening(id,cinema_id,film_id,begin_time,end_time,hall_id,hall_name,price)
	VALUES(7,6,2,'11:50','13:20',6,'5号4D厅',60);



-- ----------------------------
-- Table structure for hall_film_info
-- ----------------------------
DROP TABLE IF EXISTS `hall_film_info`;
CREATE TABLE hall_film_info(
  id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键编号',
  film_id INT COMMENT '电影编号',
  film_name VARCHAR(50) COMMENT '电影名称',
  film_length VARCHAR(50) COMMENT '电影时长',
  film_cats VARCHAR(200) COMMENT '电影类型',
  film_language VARCHAR(50) COMMENT '电影语言',
  actors VARCHAR(200) COMMENT '演员列表',
  img_address VARCHAR(500) COMMENT '图片地址'
) COMMENT '影厅电影信息表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of hall_film_info
-- ----------------------------
INSERT INTO hall_film_info(id,film_id,film_name,film_length,film_cats,actors,film_language,img_address)
	VALUES(1,2,'我不是药神',117,'喜剧,剧情','程勇,曹斌,吕受益,刘思慧','国语2D','films/238e2dc36beae55a71cabfc14069fe78236351.jpg');

INSERT INTO hall_film_info(id,film_id,film_name,film_length,film_cats,actors,film_language,img_address)
	VALUES(2,3,'爱情公寓',123,'喜剧,动作,冒险','曾小贤,胡一菲,唐悠悠,张伟','国语2D','films/238e2dc36beae55a71cabfc14069fe78236351.jpg');

	-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE order_info(
  id VARCHAR(100) COMMENT '主键编号',
  cinema_id INT COMMENT '影院编号',
  field_id INT COMMENT '放映场次编号',
  film_id INT COMMENT '电影编号',
  seats_ids VARCHAR(50) COMMENT '已售座位编号',
  seats_name VARCHAR(200) COMMENT '已售座位名称',
  film_price DOUBLE COMMENT '影片售价',
  order_price DOUBLE COMMENT '订单总金额',
  order_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  order_user INT COMMENT '下单人',
  order_status INT DEFAULT 0 COMMENT '0-待支付,1-已支付,2-已关闭'
) COMMENT '订单信息表' ENGINE = INNODB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO order_info(id,cinema_id,field_id,film_id,seats_ids,seats_name,film_price,order_price,order_user)
	VALUES('415sdf58ew12ds5fe1',1,1,2,'1,2,3,4','第一排1座,第一排2座,第一排3座,第一排4座',63.2,126.4,1);




