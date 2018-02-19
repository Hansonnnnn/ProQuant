from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
import tushare as ts

df = ts.get_today_all()
engine = create_engine('mysql+pymysql://yinywf:Wf980102@yinywf17.mysql.rds.aliyuncs.com/proQuant?charset=utf8')

DBSession = sessionmaker(bind=engine)
session = DBSession()

#session.execute('truncate stock_current_data')
#存入数据库
#df.to_sql('stock_current_data',engine,if_exists='append')



