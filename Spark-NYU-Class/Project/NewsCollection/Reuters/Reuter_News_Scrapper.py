from bs4 import BeautifulSoup
import urllib
import csv
import re
from datetime import datetime, timedelta
import time


def getNewsContent(htmlstring):
    r = urllib.urlopen(htmlstring).read()
    soup = BeautifulSoup(r)

    dateTag = soup.find_all("div", attrs={"class": re.compile("ArticleHeader_date.")})
    headerTag = soup.find_all("h1", attrs={"class": re.compile("ArticleHeader_headline.")})

    date = dateTag[0].text
    header = headerTag[0].text

    articleTag = soup.find_all("div", attrs={"class": re.compile("ArticleBody_body.")})
    article = articleTag[0].text

    return date, header, article


def getUrlsFromArchiveByDate(date):
    archivePageByDate = "http://www.reuters.com/resources/archive/us/"+date+".html"
    page = urllib.urlopen(archivePageByDate).read()
    soup = BeautifulSoup(page)

    moduleBody = soup.find_all("div", attrs={"class":"headlineMed"})

    url_list = []

    for i in range(len(moduleBody)):
        url = moduleBody[i].a["href"]
        if ("/news/picture/" in url) or ("/news/video/" in url) or ("article/pictures-report" in url):
            continue
        else:
            url_list.append(url)
            
    return url_list


def convertTimestamp(time_str):
    time_str = "JULY 12, 2017 / 1:18 PM / 10 HOURS AGO"
    time_arr = time_str.split(" / ")
    date = time_arr[0]
    hr_mi = time_arr[1]

    datetime_object = datetime.strptime(date+" "+hr_mi, '%B %d, %Y %I:%M %p')
    adjusted_EST_time = datetime_object + timedelta(hours=4)
    return str(adjusted_EST_time)


def getCsvFileByDate(date):
    urls = getUrlsFromArchiveByDate(date)
    count = 0
    with open(date+".json", "wb") as csv_file:
        csv_file.write('[\n')
        for url in urls:
            try_count = 0
            while True:
                try:
                    time_web, title, article = getNewsContent(url)
                    adjusted_time = convertTimestamp(time_web)

                    adjusted_time = "\"news_time\":\"" + adjusted_time + "\""
                    title = "\"news_title\":\"" + title.replace('\"',' ').encode("utf-8") + "\""
		            article = "\"content\":\"" + article.encode("utf-8").replace('\n',' ').replace('\r',' ').replace('\t',' ').replace('\"',' ') + "\""
                    line = '{' + adjusted_time + ',' + title + ',' + article + '}'

                    csv_file.write(line)
                    csv_file.write('\n')
                    count += 1
                    print("Finished "+str(count)+" - "+url)
                    break
                except:
                    print("Something wrong happened! try_count = "+str(try_count))
                    if try_count <= 4:
                        time.sleep(10)
                        try_count += 1
                    else:
                        print("URL Does Not Work! "+url)
                        break
    csv_file.write(']')
    csv_file.close()
    print(count)

dates = ["20170712", "20170711", "20170710","20170709","20170708","20170707","20170706","20170705","20170704","20170703","20170702","20170701"]

for date in dates:
    getCsvFileByDate(date)

