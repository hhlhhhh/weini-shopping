
export const dealMediaUrl = (goodItem)=>{
    const imageReg = /\.(jpg|jpeg|png|bmp|gif)$/
    for (let i = 0 ;i < goodItem['mediaUrlList'].length; i++) {
        goodItem['mediaUrlList'][i].url = process.env.BASE_MEDIA+goodItem['mediaUrlList'][i].url
        if(imageReg.test(goodItem['mediaUrlList'][i].url)){
            if(!goodItem['image-url']){
                goodItem['image-url'] = goodItem['mediaUrlList'][i].url
                goodItem['media-type'] = "image"
            }
        }
    }
    if(goodItem['mediaUrlList'].length===0){
        goodItem['image-url'] = process.env.BASE_MEDIA+"default-avatar.png"
        goodItem['media-type'] = "image"
    }else if(!goodItem['image-url']){
        goodItem['image-url'] = goodItem['mediaUrlList'][0].url;
        goodItem['media-type'] = "video"
    }
}