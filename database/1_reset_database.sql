-- Kustutab public schema (mis põhimõtteliselt kustutab kõik tabelid)
DROP SCHEMA IF EXISTS pearls CASCADE;
-- Loob uue public schema vajalikud õigused
CREATE SCHEMA pearls
-- taastab vajalikud andmebaasi õigused
    GRANT ALL ON SCHEMA pearls TO postgres;
GRANT ALL ON SCHEMA pearls TO PUBLIC;