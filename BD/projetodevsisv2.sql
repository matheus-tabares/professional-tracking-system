-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 29-Jun-2016 às 22:55
-- Versão do servidor: 10.1.10-MariaDB
-- PHP Version: 7.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projetodevsisv2`
--
CREATE DATABASE IF NOT EXISTS `projetodevsisv2` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `projetodevsisv2`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `nome` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`id`, `descricao`, `nome`) VALUES
(1, 'TODOS OS PROFISSIONAIS...', 'TODOS'),
(2, 'O CARPINTEIRO FAZ...', 'CARPINTEIRO'),
(3, 'O ELETRICISTA FAZ...', 'ELETRICISTA'),
(4, 'O ENCANADOR FAZ...', 'ENCANADOR'),
(5, 'O ESTOFADOR FAZ...', 'ESTOFADOR'),
(6, 'O FAXINEIRO FAZ...', 'FAXINEIRO'),
(7, 'O JARDINEIRO FAZ...', 'JARDINEIRO'),
(8, 'O MARCENEIRO FAZ...', 'MARCENEIRO'),
(9, 'O MECÂNICO FAZ...', 'MECÂNICO'),
(10, 'O PEDREIRO FAZ...', 'PEDREIRO'),
(11, 'O PINTOR FAZ...', 'PINTOR');

-- --------------------------------------------------------

--
-- Estrutura da tabela `contato`
--

CREATE TABLE `contato` (
  `id` int(11) NOT NULL,
  `assunto` varchar(255) DEFAULT NULL,
  `mensagem` varchar(255) DEFAULT NULL,
  `destinatario_id` int(11) DEFAULT NULL,
  `remetente_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `id` int(11) NOT NULL,
  `CEP` varchar(8) DEFAULT NULL,
  `UF` varchar(2) DEFAULT NULL,
  `bairro` varchar(40) DEFAULT NULL,
  `cidade` varchar(40) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(40) DEFAULT NULL,
  `numero` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `endereco`
--

INSERT INTO `endereco` (`id`, `CEP`, `UF`, `bairro`, `cidade`, `complemento`, `logradouro`, `numero`, `usuario_id`) VALUES
(1, '91790425', 'RS', 'Restinga', 'Porto Alegre', '', 'Rua Jacques Yves Costeau', 179, 1),
(2, '91790425', 'RS', 'Restinga', 'Porto Alegre', '', 'Rua Jacques Yves Costeau', 179, 2),
(3, '90160090', 'RS', 'Praia de Belas', 'Porto Alegre', '', 'Avenida Ipiranga', 179, 3),
(4, '91790000', 'RS', 'Restinga', 'Porto Alegre', '', 'Avenida Economista Nilo Wulff', 158, 4),
(5, '91755831', 'RS', 'Hípica', 'Porto Alegre', '', 'Avenida Juca Batista', 4371, 5),
(6, '90810240', 'RS', 'Praia de Belas', 'Porto Alegre', '', 'Avenida Padre Cacique', 59, 6),
(7, '91910001', 'RS', 'Tristeza', 'Porto Alegre', '', 'Avenida Otto Niemeyer', 2419, 7),
(8, '90030002', 'RS', 'Centro Histórico', 'Porto Alegre', '', 'Rua Voluntários da Pátria', 189, 8),
(9, '90470320', 'RS', 'Três Figueiras', 'Porto Alegre', '', 'Rua Primeiro de Janeiro', 158, 9),
(10, '91130720', 'RS', 'Sarandi', 'Porto Alegre', '', 'Avenida Sertório', 7000, 10),
(11, '91791508', 'RS', 'Restinga', 'Porto Alegre', '', 'Rua Alberto Hoffmann', 285, 11);

-- --------------------------------------------------------

--
-- Estrutura da tabela `mural`
--

CREATE TABLE `mural` (
  `id` int(11) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `usuarioQuePublicou_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `profissional`
--

CREATE TABLE `profissional` (
  `id` int(11) NOT NULL,
  `descricaoProfissional` varchar(255) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `profissional`
--

INSERT INTO `profissional` (`id`, `descricaoProfissional`, `categoria_id`, `usuario_id`) VALUES
(1, 'eletrecista dos bons', 3, 1),
(2, NULL, 7, 2),
(3, 'pinto muito', 11, 3),
(4, 'Gosto de madeira dura', 2, 4),
(5, 'coloco cano em tudo até em pia', 4, 5),
(6, 'estofo fofo afofo', 5, 6),
(7, 'limpo com limpol', 6, 7),
(8, 'coloco pau e serro', 8, 8),
(9, 'sou engraxado', 9, 9),
(10, 'pedra pedrão só chama ', 10, 10),
(11, 'pinto com pincel', 11, 11);

-- --------------------------------------------------------

--
-- Estrutura da tabela `seguranca`
--

CREATE TABLE `seguranca` (
  `id` int(11) NOT NULL,
  `SALT` varchar(255) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `seguranca`
--

INSERT INTO `seguranca` (`id`, `SALT`, `usuario_id`) VALUES
(1, '8b7ae907-daa8-47b4-8', 1),
(2, '9b064d31-2d7a-4a23-b', 2),
(3, 'dd0037e0-6df4-491e-8', 3),
(4, 'dbc3789b-fdaa-4c10-b', 4),
(5, '20a0cc0f-2cc5-47c5-a', 5),
(6, 'ddb5b75e-979a-4b77-8', 6),
(7, 'ca4bf23c-d826-45ab-8', 7),
(8, 'c0ec5052-1477-4a45-b', 8),
(9, '8a1aab70-b951-496c-8', 9),
(10, '88763601-af84-4384-b', 10),
(11, 'c741a0a1-adb7-4f47-a', 11);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `CPF` varchar(255) DEFAULT NULL,
  `administrador` bit(1) NOT NULL,
  `ehProfissional` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `CPF`, `administrador`, `ehProfissional`, `email`, `foto`, `nome`, `senha`, `telefone`) VALUES
(1, '111111111111', b'0', b'1', 'icorrea@gmail.com', 'resources/imagens/silhuetaHomem.jpg', 'igor correa', '6cf920597c2cc69af16cf033c684d0df9d2e2b172c0417e0dd4ee2d5b4938edda096ed91874dbeabccc41fdddd7356ec944917bd07ec37cd424de2989ed6b0fd', '111111111111111'),
(2, '111111111111', b'0', b'1', 'xxxxx@gmail.com', 'resources/imagens/silhuetaHomem.jpg', 'jardinero', '3e4e9533e0a34dfe9b21f5f8467d2c4021138e0153aa0f87bf48aafa2ffd789d84bfa2993b3840d24de1961462a57d1b5b1f6a5988618c219bfb7b031f07e682', '111111111111111'),
(3, '555555555555', b'0', b'1', 'pintor@pintor.com.br', 'resources/imagens/silhuetaHomem.jpg', 'paulo pintor', '4494a1e90ac255bfb4dd0e81131b871cd5295355162597a1b965fccc9a0aed6848f76518cfcb2ddfad0e6e198f37eca443c1d9d7ed28791a03a8cc910bf93e16', '111111111111111'),
(4, '123452254544', b'0', b'1', 'joao@gmail.com', 'resources/imagens/silhuetaHomem.jpg', 'joão carpidor', 'f21b8708e1deb4fe719e6360140b8a692802f33fdf9f2f63fc8bb246a08cdc205e4650999fadeda4f2e4a365eef499278202cfb5816ef911d55bb3fab10a959c', '555555555555555'),
(5, '515441498499', b'0', b'1', 'encanador@encanador.com', 'resources/imagens/silhuetaHomem.jpg', 'julio encanador', '946f704613c3f210dc898255c313c0a81b38ae7b6a2de16d9dfc909d47838eb797e73b6e574c94e65a2522b20cb301a0f5c00a54a349bfabee946ebe8adb8c71', '777777777777777'),
(6, '145846546687', b'0', b'1', 'estofador@estofador', 'resources/imagens/silhuetaHomem.jpg', 'Maria estofa', 'fb254b0f88c24c0cd173b8f2554bc7463b0fc34460fd427806b3c2976d0c4958fdadb42f60e642c5e71d44ded3fd76a7db263879064625bd5ef45c8bc9c12d04', '115458574888441'),
(7, '541654488974', b'0', b'1', 'faxinas@gmail.com', 'resources/imagens/silhuetaHomem.jpg', 'Helio faxinas', '617cfc031ffe8d914bb2507aa12d2c4381c351014c6f945940b94f6aa0d9bd052f7da76d56eb91957045b01d43006faf51e82a03451e76ba0f6c7e50154b9c12', '114544444444444'),
(8, '123456654444', b'0', b'1', 'marceneiro@marceneiro.com.br', 'resources/imagens/silhuetaHomem.jpg', 'pedro do pau', 'd4bb7e5414172d06ea824cdd6ec856b6759854ec26588d5a23dee19158db2fbf5908536c304f7afbebaabb1f066fb8f6a927fe854700e9cad394f5ba896b8df2', '111111111111111'),
(9, '111111111111', b'0', b'1', 'mecanico@mecanico.com.br', 'resources/imagens/silhuetaHomem.jpg', 'Bruno graxa', 'd936337b2d1bda4f49cc23fd51ef0c93f61371568f06e6acc7d690931021e897446ba5869e36f3da54a96916012ff314b3cabc0acaee703473a2d7f8282114c1', '545454554554544'),
(10, '114444444444', b'0', b'1', 'pedreiro@pedreiro.com', 'resources/imagens/silhuetaHomem.jpg', 'Mão pesada', 'cba3fd014d0c4270d6d8c8e481607279b2c93e34c20ad7400532678b3c416211f0768c6b8dc199288b821444c1673691df81af4f87bbbf4b4cef3d4621107975', '151515155155151'),
(11, '151515155155', b'0', b'1', 'pintor@pintor.com.br', 'resources/imagens/silhuetaHomem.jpg', 'Samuel pintor', '8658a1e148cd8bfe84bdbf885afdc9f9b20d79b93659567a0b486f03bba67e2f47cbc5d50de61bf625fa7405bdfdce3660e8bc8c15ce55cf4dabe0c5239d0ebf', '115458574888441');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contato`
--
ALTER TABLE `contato`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_cpnv42c50g85fij9d6w6w8980` (`destinatario_id`),
  ADD KEY `FK_9vc84xqfroeu2i3sshijatiy0` (`remetente_id`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_afwydav7mck6ptuk011deyjjb` (`usuario_id`);

--
-- Indexes for table `mural`
--
ALTER TABLE `mural`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_hxrvsdd29i59pd89t7qjbksmq` (`categoria_id`),
  ADD KEY `FK_gxpw3bnftkcmygv26jwoabl04` (`usuarioQuePublicou_id`);

--
-- Indexes for table `profissional`
--
ALTER TABLE `profissional`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_t7sdijsej0n74655psx1kgvj5` (`categoria_id`),
  ADD KEY `FK_dql21fa6s5vdkyii0o9grhrwm` (`usuario_id`);

--
-- Indexes for table `seguranca`
--
ALTER TABLE `seguranca`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_b29cttfxr0d1y78ohb4mv9pbp` (`usuario_id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `contato`
--
ALTER TABLE `contato`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `mural`
--
ALTER TABLE `mural`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `profissional`
--
ALTER TABLE `profissional`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `seguranca`
--
ALTER TABLE `seguranca`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `contato`
--
ALTER TABLE `contato`
  ADD CONSTRAINT `FK_9vc84xqfroeu2i3sshijatiy0` FOREIGN KEY (`remetente_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK_cpnv42c50g85fij9d6w6w8980` FOREIGN KEY (`destinatario_id`) REFERENCES `usuario` (`id`);

--
-- Limitadores para a tabela `endereco`
--
ALTER TABLE `endereco`
  ADD CONSTRAINT `FK_afwydav7mck6ptuk011deyjjb` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Limitadores para a tabela `mural`
--
ALTER TABLE `mural`
  ADD CONSTRAINT `FK_gxpw3bnftkcmygv26jwoabl04` FOREIGN KEY (`usuarioQuePublicou_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK_hxrvsdd29i59pd89t7qjbksmq` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);

--
-- Limitadores para a tabela `profissional`
--
ALTER TABLE `profissional`
  ADD CONSTRAINT `FK_dql21fa6s5vdkyii0o9grhrwm` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `FK_t7sdijsej0n74655psx1kgvj5` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);

--
-- Limitadores para a tabela `seguranca`
--
ALTER TABLE `seguranca`
  ADD CONSTRAINT `FK_b29cttfxr0d1y78ohb4mv9pbp` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
