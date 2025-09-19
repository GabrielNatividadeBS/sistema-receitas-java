-- =====================================================================
-- SCRIPT COMPLETO PARA CRIAR E POPULAR O BANCO DE DADOS 'receitas_db'
-- =====================================================================

-- 1. APAGA (se existir) E CRIA O BANCO DE DADOS
DROP DATABASE IF EXISTS `receitas_db`;
CREATE DATABASE `receitas_db`;
USE `receitas_db`;

-- 2. CRIA A TABELA 'categoria' (deve ser criada antes de 'receita' por causa da chave estrangeira)
DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB;

-- 3. INSERE OS DADOS INICIAIS NA TABELA 'categoria'
INSERT INTO `categoria` (`nome`) VALUES
('Almoço'),
('Bebida'),
('Doce'),
('Jantar'),
('Salgado'),
('Sobremesa');

-- 4. CRIA A TABELA 'receita'
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

-- 5. INSERE UMA RECEITA DE EXEMPLO NA TABELA 'receita'
INSERT INTO `receita` (nome, ingredientes, modo_preparo, tempo_preparo, categoria_id)
VALUES (
  'Bolo de Cenoura', -- Nome corrigido para combinar com os ingredientes
  '3 cenouras, 2 xícaras de farinha, 3 ovos, 1 xícara de óleo, 2 xícaras de açúcar, 1 colher de fermento',
  'Bata as cenouras com ovos e óleo no liquidificador. Em uma tigela, misture a farinha e o açúcar. Despeje a mistura do liquidificador e mexa bem. Adicione o fermento, misture delicadamente e asse em forno pré-aquecido a 180°C por 40 minutos.',
  40,
  3 
);

-- 6. VERIFICA OS DADOS INSERIDOS
SELECT * FROM categoria;
SELECT * FROM receita;


SELECT
  r.nome AS 'Nome da Receita',
  c.nome AS 'Categoria'
FROM
  receita r
JOIN
  categoria c ON r.categoria_id = c.id;