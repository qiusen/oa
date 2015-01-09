修改system.properties，配置以下必选参数
	jdbc.url
	jdbc.username
	jdbc.password
	system.base
	system.default-schema
修改log4j。properties,配置以下必选参数
	log4j.appender.dblog.URL
	
	
流程管理
	流程部署
	任务管理


使用“-”连接流程key和业务表单ID，作为businessKey 使用来关联业务，例如：leaveBill-1	

所有带分支流程，使和cv控制流向 cv='y', cv='n'

使用删除运行时流程机制 处理取消流程的需求