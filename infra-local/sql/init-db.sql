CREATE TABLE categoria(
    id_categoria SERIAL NOT NULL,
    nome VARCHAR(100) NOT NULL,
    PRIMARY KEY(id_categoria)
);

CREATE TABLE produto(
    id_produto SERIAL NOT NULL,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    preco NUMERIC NOT NULL,
    imagem VARCHAR(200) NOT NULL,
    ativo BOOLEAN NOT NULL,
    id_categoria INTEGER NOT NULL,
    PRIMARY KEY(id_produto),
    CONSTRAINT fk_produto_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE cliente(
    cpf VARCHAR(11) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    PRIMARY KEY(cpf)
);

CREATE TABLE status_pedido(
    id_status_pedido SERIAL NOT NULL,
    nome VARCHAR(100) NOT NULL,
    PRIMARY KEY(id_status_pedido)
);

CREATE TABLE pedido(
    id_pedido SERIAL NOT NULL,
    cpf VARCHAR(11),
    id_status_pedido INTEGER NOT NULL,
    PRIMARY KEY(id_pedido),
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (cpf) REFERENCES cliente(cpf),
    CONSTRAINT fk_pedido_statuspedido FOREIGN KEY (id_status_pedido) REFERENCES status_pedido(id_status_pedido)
);

CREATE TABLE pedido_produto(
    id_pedido_produto SERIAL NOT NULL,
    id_pedido INTEGER NOT NULL,
    id_produto INTEGER NOT NULL,
    PRIMARY KEY(id_pedido_produto),
    CONSTRAINT fk_pedidoproduto_pedido FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    CONSTRAINT fk_pedidoproduto_produto FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);

CREATE TABLE status_pagamento(
    id_status_pagamento SERIAL NOT NULL,
    nome VARCHAR(100) NOT NULL,
    PRIMARY KEY(id_status_pagamento)
);

CREATE TABLE pagamento(
    id_pagamento SERIAL NOT NULL,
    id_pedido INTEGER NOT NULL,
    id_status_pagamento INTEGER NOT NULL,
    PRIMARY KEY(id_pagamento),
    CONSTRAINT fk_pagamento_pedido FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    CONSTRAINT fk_pagamento_statuspagamento FOREIGN KEY (id_status_pagamento) REFERENCES status_pagamento(id_status_pagamento)
);

INSERT INTO categoria (id_categoria, nome) VALUES (1, 'Lanche');
INSERT INTO categoria (id_categoria, nome) VALUES (2, 'Acompanhamento');
INSERT INTO categoria (id_categoria, nome) VALUES (3, 'Bebida');
INSERT INTO categoria (id_categoria, nome) VALUES (4, 'Sobremesa');

INSERT INTO status_pedido (id_status_pedido, nome) VALUES (1, 'RECEBIDO');
INSERT INTO status_pedido (id_status_pedido, nome) VALUES (2, 'EM PREPARACAO');
INSERT INTO status_pedido (id_status_pedido, nome) VALUES (3, 'PRONTO');
INSERT INTO status_pedido (id_status_pedido, nome) VALUES (4, 'FINALIZADO');
INSERT INTO status_pedido (id_status_pedido, nome) VALUES (5, 'CANCELADO');

INSERT INTO status_pagamento(id_status_pagamento, nome) VALUES (1, 'PENDENTE');
INSERT INTO status_pagamento(id_status_pagamento, nome) VALUES (2, 'APROVADO');
INSERT INTO status_pagamento(id_status_pagamento, nome) VALUES (3, 'REJEITADO');
INSERT INTO status_pagamento(id_status_pagamento, nome) VALUES (4, 'CANCELADO');