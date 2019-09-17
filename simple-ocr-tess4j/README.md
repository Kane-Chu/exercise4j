# simple ocr base on tess4j

目前识别率不高，还未找到解决办法。

## 安装tesseract

MAC OSX
```bash
> brew install tesseract
```


## 下载tessdata

下载[tessreact](https://github.com/tesseract-ocr/tesseract)项目里面的tessdata文件夹放到某个位置，
本例中放在了[resources](src/main/resources/)目录下面



## 下载相关语言包的训练文件

  * 从[tessdata](https://github.com/tesseract-ocr/tessdata)中下载对应的训练文件。
  * 如果是简单的英文数字验证码，下载 eng.traineddata然后放到文件夹里即可，中文的是chi开头的traineddata。
  * 将下载好的文件放入tessdata文件夹中，本例在[resources/tessdata](src/main/resources/tessdata)


