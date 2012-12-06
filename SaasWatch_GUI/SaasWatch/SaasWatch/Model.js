
guidedModel =// @startlock
{
	Connections :
	{
		events :
		{
			onLoad:function()
			{// @endlock
				var dateEntryArray = this.dateOfEntry.split(" ");
        		var dateString = dateEntryArray[0];
        		this.year = dateString.split("-")[0];
        		this.month = dateString.split("-")[1];
        		this.day = dateString.split("-")[2];
        		var timeString = dateEntryArray[1];
				this.hour = timeString.split(":")[0];
				this.minute = timeString.split(":")[1];
				this.numberOfConnections = this.allConnections.length;
			}// @startlock
		}
	},
	Disk :
	{
		events :
		{
			onLoad:function()
			{// @endlock
				var dateEntryArray = this.dateOfEntry.split(" ");
        		var dateString = dateEntryArray[0];
        		this.year = dateString.split("-")[0];
        		this.month = dateString.split("-")[1];
        		this.day = dateString.split("-")[2];
        		var timeString = dateEntryArray[1];
				this.hour = timeString.split(":")[0];
				this.minute = timeString.split(":")[1];
			}// @startlock
		}
	},
	CPU :
	{
		events :
		{
			onLoad:function()
			{// @endlock
				var dateEntryArray = this.dateOfEntry.split(" ");
        		var dateString = dateEntryArray[0];
        		this.year = dateString.split("-")[0];
        		this.month = dateString.split("-")[1];
        		this.day = dateString.split("-")[2];
        		var timeString = dateEntryArray[1];
				this.hour = timeString.split(":")[0];
				this.minute = timeString.split(":")[1];
			}// @startlock
		}
	},
	Memory :
	{
		events :
		{
			onLoad:function()
			{// @endlock
				var dateEntryArray = this.dateOfEntry.split(" ");
        		var dateString = dateEntryArray[0];
        		this.year = dateString.split("-")[0];
        		this.month = dateString.split("-")[1];
        		this.day = dateString.split("-")[2];
        		var timeString = dateEntryArray[1];
				this.hour = timeString.split(":")[0];
				this.minute = timeString.split(":")[1];
//				this.fullDate = new Date(this.dateOfEntry);
//				this.year = this.fullDate.getFullYear();
//				this.month = this.fullDate.getMonth() + 1;
//				this.date = this.fullDate.getDate();
//				this.hour = this.fullDate.getHours();
//				this.minute = this.fullDate.getMinutes();
			}// @startlock
		}
	}
};// @endlock
