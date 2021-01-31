-- Table for characters
CREATE TABLE character
(
    id             INTEGER,
    marvelId       INTEGER,
    nickname       TEXT,
    full_name      TEXT,
    must_be_synced BOOLEAN   default FALSE,
    last_sync_at   TIMESTAMP,
    created_at     TIMESTAMP default current_timestamp,
    updated_at     TIMESTAMP,
    PRIMARY KEY (id)
);

-- Table for comics
CREATE TABLE comic
(
    id         INTEGER,
    marvelId   INTEGER,
    title      TEXT,
    created_at TIMESTAMP default current_timestamp,
    updated_at TIMESTAMP,
    PRIMARY KEY (id)
);

-- Table for creators
CREATE TABLE creator
(
    id          INTEGER,
    marvelId    INTEGER,
    name        TEXT,
    resourceURI TEXT,
    role        VARCHAR(32),
    PRIMARY KEY (id)
);

-- To store relations between comic and their characters
CREATE TABLE comic_character
(
    comicId     INTEGER,
    characterId INTEGER,
    FOREIGN KEY (characterId) REFERENCES character (id) ON DELETE CASCADE,
    FOREIGN KEy (comicId) REFERENCES comic (id) ON DELETE CASCADE
);


-- To store relations between comics and their creator
CREATE TABLE comic_creator
(
    comicId   INTEGER,
    creatorId INTEGER,
    FOREIGN KEY (comicId) REFERENCES comic (id) ON DELETE CASCADE,
    FOREIGN KEY (creatorId) REFERENCES creator (id) ON DELETE CASCADE
);

-- Indexes
CREATE
INDEX comic_character_comicid_index ON comic_character(comicId);
CREATE
INDEX comic_character_characterid_index ON comic_character(characterId);

CREATE
INDEX comic_creator_comicid_index ON comic_creator(comicId);
CREATE
INDEX comic_creator_characterid_index ON comic_creator(creatorId);