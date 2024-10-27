SELECT
   SUBQUERY.PRODUCT_ID,
   SUBQUERY.BRAND_ID,
   SUBQUERY.PRICE_LIST,
   SUBQUERY.START_DATE,
   SUBQUERY.END_DATE,
   SUBQUERY.PRICE,
   SUBQUERY.CURRENCY
FROM
 (
    SELECT
       P.PRODUCT_ID,
       P.BRAND_ID,
       P.PRICE_LIST,
       P.START_DATE,
       P.END_DATE,
       P.PRICE,
       P.CURRENCY,
       P.PRIORITY
    FROM PRICES P
    WHERE P.PRODUCT_ID = :PRODUCT_ID
    AND P.BRAND_ID = :BRAND_ID
    AND :APPLICATION_DATE BETWEEN P.START_DATE AND P.END_DATE
 ) SUBQUERY
ORDER BY SUBQUERY.PRIORITY DESC
LIMIT 1;