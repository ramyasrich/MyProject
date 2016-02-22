====================== Start problem description ==================

Exercise: Using any language of your choice write a script that does the following: 
Call http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=1

To go to next page of response you have to increment the page number in the above url. As long as the "more" field returns true, you have more data available.

The response is a JSON object which has a response key which is an array of more JSON objects. Each of them has a key called flags and within flags there is a key called hd.

Print out how many response objects have flags:hd set to true and how many have hd set to false.

Finally, please check in your code onto GitHub and share the link.

====================== End problem description ==================
