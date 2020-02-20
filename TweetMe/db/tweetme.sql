-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Máquina: 127.0.0.1
-- Data de Criação: 22-Nov-2013 às 23:55
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
(1, 6),
(14, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `posts`
--

CREATE TABLE IF NOT EXISTS `posts` (
  `id` double NOT NULL AUTO_INCREMENT,
  `id_user` double NOT NULL,
  `textPost` varchar(150) NOT NULL,
  `datePost` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `INDEX_posts_users` (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Extraindo dados da tabela `posts`
--

INSERT INTO `posts` (`id`, `id_user`, `textPost`, `datePost`) VALUES
(1, 3, 'Kadija  Rd - Gamblin International, LLC - CEZURA.', '2013-10-23 14:25:41'),
(2, 5, 'Rainbow Creek - Yin Engineers, Ltd - PARADA (CASTRELO DE MIÑO).', '2013-10-23 14:25:41'),
(3, 2, 'Bronze Street - Gorelick & Sons Consulting, LLC - Fürth.', '2013-10-23 14:25:41'),
(4, 4, 'Hawick  Court - Walles & Associates Solutions, LLC - Poesele.', '2013-10-23 14:25:41'),
(5, 1, 'Yellow Peak Pl - Pushaw & Associates Distributing Company, LLC - BIURRUN.', '2013-10-23 14:25:41'),
(6, 2, 'St James Cottage - Buckelew Engineers - Kuzemerbalk.', '2013-10-23 14:25:41'),
(7, 5, 'JUAN DIAZ PATIO - Younker Recruiting, Ltd - ESTICHE DE CINCA.', '2013-10-23 14:25:41'),
(8, 3, 'Aroona  Tce - Prioletti ICT - Belfort.', '2013-10-23 14:25:41'),
(9, 4, 'Kinnoul Road - Sobeski Distributing Company, Inc - ARDANUE.', '2013-10-23 14:25:41'),
(10, 3, 'RIO GUADALMEDINA - Hise Printing - CHAO (FERREIRA).', '2013-10-23 14:25:41'),
(11, 2, 'Thompson Rd - Louro Engineers, LLC - Derrick City.', '2013-10-23 14:25:41'),
(12, 2, 'ISABEL PANTOJA - Cheverez Printing - Hampton.', '2013-10-23 14:25:41'),
(13, 2, 'Kanangra  Ave - Raeside Communication - Acampo.', '2013-10-23 14:25:41'),
(14, 4, 'Gettysburg St - Khalifah & Sons Communication - Chino.', '2013-10-23 14:25:41'),
(15, 2, 'Lupus Street - Kross & Associates Printing - Glyndon.', '2013-10-23 14:25:41'),
(16, 2, 'Grayland Villas - Roblodowski & Co International - CIVICO.', '2013-10-23 14:25:41'),
(17, 2, 'Montgomery  Ave - Richters Printing - PERILLA DE CASTRO.', '2013-10-23 14:25:41'),
(18, 5, 'BORREGUILES - Fellhauer & Co Solutions, Ltd - PIEDRA (POSADA LLANES).', '2013-10-23 14:25:41'),
(19, 4, 'Lower John Street - Younkins & Co Solutions - Velsen-Noord.', '2013-10-23 14:25:41'),
(20, 2, 'Moon Dipper Ct - Cowin Solutions, LLC - Bishops Cleeve.', '2013-10-23 14:25:41'),
(22, 6, 'Post funcionando!!!', '2013-10-24 19:05:46'),
(24, 14, 'Primeiro tweet', '2013-11-14 13:30:36'),
(25, 6, 'Outro tweet', '2013-11-14 13:32:07'),
(26, 6, 'Outro tweet', '2013-11-14 13:33:01'),
(27, 6, 'Outro tweet', '2013-11-14 13:34:05'),
(29, 15, 'Primeiro post Anderson!', '2013-11-21 11:12:07'),
(30, 6, 'asdasd', '2013-11-21 11:27:10'),
(31, 6, 'sda', '2013-11-21 11:27:15'),
(33, 16, 'Primeiro post! o/', '2013-11-21 12:17:18'),
(34, 16, 'Segundo post! o/', '2013-11-21 12:17:27'),
(35, 6, 'uhaiosd oashid aoishd oiasho ', '2013-11-21 18:24:09'),
(36, 18, 'Primeiro post!', '2013-11-22 21:11:02');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`id`, `login`, `pass`, `name`, `description`, `email`, `photo`) VALUES
(1, 'yvonne-arainz', '123456', 'Yvonne', 'Arainz', 'yvonne-arainz@tweetme.com', '/imgs/eu.jpg'),
(2, 'javier-tesillo', '123456', 'Javier', 'Tesillo', 'javier-tesillo@tweetme.com', '/imgs/eu.jpg'),
(3, 'jonna-fenderson', '123456', 'Jonna', 'Fenderson', 'jonna-fenderson@tweetme.com', '/imgs/eu.jpg'),
(4, 'beertje-kee', '123456', 'Beertje', 'Kee', 'beertje-kee@tweetme.com', '/imgs/eu.jpg'),
(5, 'serafim-asturiano', '123456', 'Serafim', 'Asturiano', 'serafim-asturiano@tweetme.com', '/imgs/eu.jpg'),
(6, 'fraudlucas', '123456', 'Marcus Editado', 'This is my description!', 'marcus_lucas12@outlook.com', '/imgs/eu.jpg'),
(7, 'asjajs', '123456', 'ASJ AJS', 'This ', 'asj_ajs@outlook.com', '/imgs/eu.jpg'),
(8, 'fdsfsfs', 'sdsfsdf', 'sdfsdfdsf', 'buiasbdabisd', 'DDD@sdddf', '/imgs/eu.jpg'),
(9, 'aisdoaisdaisoadsoidajsiodjapsd', 'saoidasid', 'testando signup', 'asdasdasdasd', 'asd@asdasdad.asdasdasd', '/imgs/eu.jpg'),
(11, 'babiiii', '123456', 'babiiii', 'asdasdaiob aoisb oa', 'babiiiii@asdas.asd', '/imgs/eu.jpg'),
(14, 'paulomgj', '123456', 'Paulo', 'asdnko nioa snnd as', 'paulomgj@outlook.com', '/imgs/eu.jpg'),
(15, 'anderson', '123', 'Anderson Moreira', 'ashiod hioashio ', 'anderson@rtsystems.org', '/imgs/eu.jpg'),
(16, 'hugo', '123456', 'Hugo Magalhães', 'noas jodjos ano', 'hugo.magalhaess@gmail.com', '/imgs/eu.jpg'),
(18, 'alexandre', '123', 'Alexandre', 'This is my description', 'alexandre@asdasd.asd', '/imgs/eu.jpg');

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
