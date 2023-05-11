<template>
  <div class="user-detail">
    <div class="header">
      <Header/>
    </div>
    <div class="content">
      <div class="user-mes">
        <div class="avatar">
          <a-avatar :size="100" shape="square">
            <img src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/3ee5f13fb09879ecb5185e440cef6eb9.png~tplv-uwbnlip3yd-webp.webp">
          </a-avatar>
        </div>
        <ul>
          <li>
            <div>
              <span>昵称:</span>
              <i>上单魔王上单魔王上魔王上魔王上单魔王上单魔王</i>
            </div>
          </li>
          <li>
            <div>
              <span>性别:</span>
              <i>女</i>
            </div>
          </li>
          <li>
            <div>
              <span>注册时间:</span>
              <i>2003-05-14</i>
            </div>
          </li>
        </ul>
        <div class="signature">
          <div>个性签名：</div>
          <p>我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天我命由我不由天</p>
        </div>
      </div>
      <div class="other-mes">
        <a-tabs default-active-key="1">
          <a-tab-pane key="1" title="浏览历史">
            <FootprintItem v-for="item in  footPrintData" :key="item.id" :good-item="item"></FootprintItem>
          </a-tab-pane>
          <a-tab-pane key="2" title="收藏">
            <ProfileGoodItem  v-for="item in  collectData" :key="item.id" :good-item="item"></ProfileGoodItem>
          </a-tab-pane>
        </a-tabs>
      </div>
    </div>
  </div>
</template>

<script setup>
import Header from "@/components/Header.vue";
import FootprintItem from "@/view/profile/componets/FootprintItem.vue";
import ProfileGoodItem from "@/view/profile/componets/ProfileGoodItem.vue";

let footPrintData = [
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'name': "眼镜",
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.9,
    'create-time': new Date('2023.1.6 20:20:12')
  },
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'name': "眼镜",
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.9,
    'create-time': new Date('2023.05.08 1:12:12')
  },
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'name': "眼镜",
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.5,
    'create-time': new Date('2023.1.6 11:12:02')
  },
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'name': "眼镜",
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.9,
    'create-time': new Date('2023-01-05 12:14:12')
  }
]
let collectData = [
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'name': "眼镜",
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.9,
    'create-time': new Date('2023.1.6 20:20:12')
  },
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'name': "眼镜",
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.9,
    'create-time': new Date('2023.05.08 1:12:12')
  },
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'name': "眼镜",
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.5,
    'create-time': new Date('2023.1.6 11:12:02')
  },
  {
    'id':1,
    'image-url':'https://img.pddpic.com/gaudit-image/2022-11-16/747e941c59877cbb1c4aa442130203d5.jpeg',
    'name': "眼镜",
    'desc':"Yesmoon依视明甜丧芭比半年抛2片装美瞳新款隐形眼镜大小直径女",
    'price':24.9,
    'create-time': new Date('2023-01-05 12:14:12')
  }
]

</script>

<style scoped lang="less">

.user-detail{
  .header{
    padding-bottom: 70px;
  }
  .content{
    width: 1000px;
    margin: 20px auto 0;
    .user-mes{
      display: flex;
      >ul{
        display: flex;
        flex-direction: column;
        margin-left: 25px;
        >li{
          min-width: 150px;
          overflow: hidden;
          text-overflow: ellipsis;
          display: -webkit-box;
          -webkit-line-clamp: 2;
          -webkit-box-orient: vertical;
          margin: 0 20px 10px;
          &:last-child{
            margin-bottom: 0;
          }
          i{
            color: #3c3c3c;
            padding-left: 5px;
          }
        }
      }
      .avatar{
        img{
          width: 100px;
        }
      }
      .signature{
        >p{
          text-indent: 2em;
          margin-top: 10px;
          overflow: hidden;
          text-overflow: ellipsis;
          display: -webkit-box;
          -webkit-line-clamp: 4;
          -webkit-box-orient: vertical;
        }
      }
    }
    .other-mes{
      margin-top: 20px;
    }
  }
}

</style>