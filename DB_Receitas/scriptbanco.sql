-- =====================================================================
-- SCRIPT COMPLETO PARA CRIAR E POPULAR O BANCO DE DADOS 'receitas_db'
-- =====================================================================


DROP DATABASE IF EXISTS `receitas_db`;
CREATE DATABASE `receitas_db`;
USE `receitas_db`;


DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB;


INSERT INTO `categoria` (`nome`) VALUES
('Almoço'),
('Bebida'),
('Doce'),
('Jantar'),
('Salgado'),
('Sobremesa');


DROP TABLE IF EXISTS `receita`;
CREATE TABLE `receita` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `ingredientes` TEXT,
  `modo_preparo` TEXT,
  `tempo_preparo` INT NOT NULL,
  `categoria_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_receita_nome` (`nome`),
  KEY `fk_receita_categoria` (`categoria_id`),
  CONSTRAINT `fk_receita_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`),
  CONSTRAINT `chk_tempo` CHECK ((`tempo_preparo` > 0))
) ENGINE=InnoDB;


INSERT INTO `receita` (nome, ingredientes, modo_preparo, tempo_preparo, categoria_id)
VALUES 
(
  'Bolo de Cenoura com Cobertura de Chocolate', 
  'MASSA: 3 cenouras, 3 ovos, 1 xícara de óleo, 2 xícaras de açúcar, 2 xícaras de farinha de trigo, 1 colher de sopa de fermento em pó. COBERTURA: 1 xícara de achocolatado, 1 xícara de açúcar, 2 colheres de sopa de manteiga, 1/2 xícara de leite.',
  'MASSA: Bata as cenouras, ovos e óleo no liquidificador. Em uma tigela, misture os ingredientes secos. Despeje a mistura líquida e mexa bem. Asse a 180°C por 40 minutos. COBERTURA: Misture todos os ingredientes em uma panela e leve ao fogo baixo, mexendo sempre, até engrossar. Despeje sobre o bolo ainda quente.',
  50,
  3 -- Categoria: Doce
),
(
  'Frango Xadrez',
  '500g de filé de frango em cubos, 1 pimentão verde em cubos, 1 pimentão vermelho em cubos, 1 cebola em cubos, 1/2 xícara de amendoim torrado, 4 colheres de sopa de shoyu, 1 colher de sopa de amido de milho, 1 xícara de água.',
  'Tempere o frango e frite em uma panela quente até dourar. Reserve. Na mesma panela, refogue a cebola e os pimentões. Dissolva o amido de milho na água com o shoyu. Volte o frango para a panela, adicione o molho e cozinhe até engrossar. Finalize com o amendoim.',
  30,
  4 -- Categoria: Jantar
),
(
  'Pão de Queijo Mineiro',
  '500g de polvilho azedo, 250ml de leite, 100ml de óleo, 100ml de água, 2 ovos, 200g de queijo minas padrão ralado, sal a gosto.',
  'Ferva o leite, a água, o óleo e o sal. Despeje sobre o polvilho em uma tigela para escaldar. Espere amornar. Adicione os ovos um a um, sovando bem. Por último, incorpore o queijo ralado. Faça bolinhas e asse em forno pré-aquecido a 200°C por cerca de 30 minutos ou até dourar.',
  45,
  5 -- Categoria: Salgado
),
(
  'Limonada Suíça Cremosa',
  '2 limões grandes, 700ml de água gelada, 4 colheres de sopa de açúcar, 3 colheres de sopa de leite condensado, gelo a gosto.',
  'Lave bem os limões e corte as pontas. Corte os limões em 4 partes. Coloque no liquidificador com a água e bata por cerca de 20 segundos em velocidade alta. Coe a mistura em uma peneira fina. Volte o líquido coado para o liquidificador, adicione o açúcar, o leite condensado e o gelo. Bata novamente e sirva imediatamente.',
  10,
  2 -- Categoria: Bebida
),
(
  'Mousse de Maracujá Rápido',
  '1 lata de leite condensado, 1 lata de creme de leite, 250ml de suco de maracujá concentrado.',
  'Coloque todos os ingredientes no liquidificador. Bata em velocidade máxima por aproximadamente 3 minutos, ou até a mistura ficar bem homogênea e cremosa. Despeje em uma travessa ou em taças individuais. Leve à geladeira por pelo menos 2 horas antes de servir.',
  15,
  6 -- Categoria: Sobremesa
);


SELECT * FROM categoria;
SELECT * FROM receita;



SELECT
  r.nome AS 'Nome da Receita',
  c.nome AS 'Categoria',
  r.tempo_preparo AS 'Tempo (min)'
FROM
  receita r
JOIN
  categoria c ON r.categoria_id = c.id
ORDER BY
  c.nome, r.nome;
