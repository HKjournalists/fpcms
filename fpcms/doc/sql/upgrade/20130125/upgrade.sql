alter table cms_channel modify channel_desc varchar(300);

update cms_channel set channel_desc = '${city}${company}�������޹�˾��2005����Ƴ���,�Ǿ�������׼�ľ���${city}��Ʊ�����ʸ��רҵ˰��${city}������Ʊ��˾,�ɿ�${city}���Ϸѷ�Ʊ|ס�޷ѷ�Ʊ|${city}�����ѷ�Ʊ|${city}�Ƶ귢Ʊ|���ѷ�Ʊ|�ȸ���${city}|${city}��ֵ˰��Ʊ����Ʊ��Ŀ'
where channel_code='home' ;

