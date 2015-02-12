use SURDEV

select * from t_dtl_dev_fileProcess

--Top 10 frequently used words
select top 10 count, Word from t_dtl_dev_fileProcess order by Count desc

--Top 10 least frequently used words. Though After 6859th record only count 2 starts coming. 
select top 10000 count, Word from t_dtl_dev_fileProcess order by Count asc

-- For Alphabets AND Numbers ONLY
select distinct Word from t_dtl_dev_fileProcess where 
Word like '%[a-z]%' and Word like '%[0-9]%' and Word not like '%[^0-9a-z]%'

-- Words with special characters
select distinct Word from t_dtl_dev_fileProcess where 
Word like '%[^0-9a-z]%'

--Words with only Alpha
select distinct Word from t_dtl_dev_fileProcess where 
Word not like '%[^0-9a-z]%'

