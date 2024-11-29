-- Get Answer Details
CREATE OR REPLACE FUNCTION getAnswers()
RETURNS TABLE (
    answer_id INT,
    selected_option INT,
    choice INT,
    text VARCHAR,
    answer_type INT
) AS $$
BEGIN
RETURN QUERY
SELECT
    answer."answerId",
    multiplechoiceanswer."selectedOption",
    numericrangeanswer."choice",
    textanswer."text",
    CASE
        WHEN multiplechoiceanswer."answerId" IS NOT NULL THEN 0
        WHEN numericrangeanswer."answerId" IS NOT NULL THEN 1
        WHEN textanswer."answerId" IS NOT NULL THEN 2
        ELSE NULL
        END AS answer_type
FROM answer
         LEFT JOIN multiplechoiceanswer ON answer."answerId" = multiplechoiceanswer."answerId"
         LEFT JOIN numericrangeanswer ON answer."answerId" = numericrangeanswer."answerId"
         LEFT JOIN textanswer ON answer."answerId" = textanswer."answerId";
END;
$$ LANGUAGE plpgsql;

--

