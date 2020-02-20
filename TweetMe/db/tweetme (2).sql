-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Máquina: 127.0.0.1
-- Data de Criação: 06-Dez-2013 às 20:45
-- Versão do servidor: 5.6.11
-- versão do PHP: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `tweetme`
--
CREATE DATABASE IF NOT EXISTS `tweetme` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `tweetme`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `follow`
--

CREATE TABLE IF NOT EXISTS `follow` (
  `id_followed` double NOT NULL,
  `id_follower` double NOT NULL,
  PRIMARY KEY (`id_followed`,`id_follower`),
  KEY `FK_follower_user` (`id_follower`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `follow`
--

INSERT INTO `follow` (`id_followed`, `id_follower`) VALUES
(2, 1),
(4, 1),
(4, 2),
(6, 2),
(14, 2),
(1, 3),
(2, 3),
(6, 3),
(1, 4),
(2, 4),
(3, 4),
(2, 5),
(15, 6),
(19, 6),
(6, 19);

-- --------------------------------------------------------

--
-- Estrutura da tabela `posts`
--

CREATE TABLE IF NOT EXISTS `posts` (
  `id` double NOT NULL AUTO_INCREMENT,
  `id_user` double NOT NULL,
  `id_author` double NOT NULL,
  `shared` tinyint(1) NOT NULL DEFAULT '0',
  `textPost` varchar(150) NOT NULL,
  `datePost` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `INDEX_posts_users` (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=75 ;

--
-- Extraindo dados da tabela `posts`
--

INSERT INTO `posts` (`id`, `id_user`, `id_author`, `shared`, `textPost`, `datePost`) VALUES
(64, 6, 6, 0, 'asdasdasd', '2013-12-06 16:50:51'),
(65, 15, 6, 0, 'ccxvcbvcbcvb df gdfg ', '2013-12-06 16:51:21'),
(66, 15, 15, 0, 'ccxvcbvcbcvb df gdfg ', '2013-12-06 16:51:21'),
(67, 15, 15, 0, 'as dasd as da', '2013-12-06 16:51:36'),
(68, 6, 15, 0, 'as dasd as da', '2013-12-06 16:51:36'),
(69, 6, 15, 0, 'as dasd as da', '2013-12-06 16:51:36'),
(70, 6, 6, 0, 'assadsdas', '2013-12-06 18:45:05'),
(71, 6, 15, 0, 'ccxvcbvcbcvb df gdfg ', '2013-12-06 19:10:43'),
(72, 6, 15, 0, 'ccxvcbvcbcvb df gdfg ', '2013-12-06 19:11:32'),
(73, 6, 15, 0, 'ccxvcbvcbcvb df gdfg ', '2013-12-06 19:12:00'),
(74, 6, 15, 0, 'ccxvcbvcbcvb df gdfg ', '2013-12-06 19:40:25');

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` double NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(150) DEFAULT 'This is my description',
  `email` varchar(255) NOT NULL,
  `photo` varchar(100) NOT NULL DEFAULT '/imgs/eu.jpg',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UQ_login` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`id`, `login`, `pass`, `name`, `description`, `email`, `photo`) VALUES
(1, 'yvonne-arainz', '123456', 'Yvonne', 'Arainz', 'yvonne-arainz@tweetme.com', '/imgs/eu.jpg'),
(2, 'javier-tesillo', '123456', 'Javier', 'Tesillo', 'javier-tesillo@tweetme.com', '/imgs/eu.jpg'),
(3, 'jonna-fenderson', '123456', 'Jonna', 'Fenderson', 'jonna-fenderson@tweetme.com', '/imgs/eu.jpg'),
(4, 'beertje-kee', '123456', 'Beertje', 'Kee', 'beertje-kee@tweetme.com', '/imgs/eu.jpg'),
(5, 'serafim-asturiano', '123456', 'Serafim', 'Asturiano', 'serafim-asturiano@tweetme.com', '/imgs/eu.jpg'),
(6, 'fraudlucas', '123456', 'Marcus asidoaish', 'This is my description!', 'marcus_lucas12@outlook.com', '/imgs/profiles/6.0/me.jpg'),
(7, 'asjajs', '123456', 'ASJ AJS', 'This ', 'asj_ajs@outlook.com', '/imgs/eu.jpg'),
(8, 'fdsfsfs', 'sdsfsdf', 'sdfsdfdsf', 'buiasbdabisd', 'DDD@sdddf', '/imgs/eu.jpg'),
(9, 'aisdoaisdaisoadsoidajsiodjapsd', 'saoidasid', 'testando signup', 'asdasdasdasd', 'asd@asdasdad.asdasdasd', '/imgs/eu.jpg'),
(11, 'babiiii', '123456', 'babiiii', 'asdasdaiob aoisb oa', 'babiiiii@asdas.asd', '/imgs/eu.jpg'),
(14, 'paulomgj', '123456', 'Paulo', 'asdnko nioa snnd as', 'paulomgj@outlook.com', '/imgs/eu.jpg'),
(15, 'anderson', '123', 'Anderson Moreira', 'ashiod hioashio ', 'anderson@rtsystems.org', '/imgs/eu.jpg'),
(16, 'hugo', '123456', 'Hugo Magalhães', 'noas jodjos ano', 'hugo.magalhaess@gmail.com', '/imgs/eu.jpg'),
(18, 'alexandre', '123', 'Alexandre', 'This is my description', 'alexandre@asdasd.asd', '/imgs/eu.jpg'),
(19, 'simone', '123', 'Simone', 'This is my description', 'simone@asiodas.asd', '/imgs/eu.jpg'),
(20, 'max', '123', 'Max', 'This is my description', 'maxwell@asdla.asd', '/imgs/eu.jpg');

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `follow`
--
ALTER TABLE `follow`
  ADD CONSTRAINT `FK_followed_user` FOREIGN KEY (`id_followed`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK_follower_user` FOREIGN KEY (`id_follower`) REFERENCES `users` (`id`);

--
-- Limitadores para a tabela `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `FK_posts_users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
