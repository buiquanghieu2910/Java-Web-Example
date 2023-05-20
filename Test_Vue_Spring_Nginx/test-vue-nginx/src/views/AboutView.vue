<template>
  <div class="about">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
// import { HTTP } from '@/http-common';
import axios from 'axios';

export default {
  data() {
    return {
      msg: 'This is an about page'
    }
  },
  mounted() {
    axios.get('/api/test').then((resp) => {
      console.log(resp);
      this.msg = resp.data.fullname;
    }).catch((error) => {
      console.log(error);
    })
  }
}
</script>