CREATE TABLE users (id int(11) NOT NULL AUTO_INCREMENT,name varchar(255) DEFAULT NULL,password varchar(255) DEFAULT NULL, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE task (
  id int(11) NOT NULL AUTO_INCREMENT,
  date date DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  state varchar(255) DEFAULT NULL,
  user_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKbhwpp8tr117vvbxhf5sbkdkc9 (user_id),
  CONSTRAINT FKbhwpp8tr117vvbxhf5sbkdkc9 FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE subtask (
  id int(11) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  task_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKqogp98iwmph591yu58frt48ud (task_id),
  CONSTRAINT FKqogp98iwmph591yu58frt48ud FOREIGN KEY (task_id) REFERENCES task (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

