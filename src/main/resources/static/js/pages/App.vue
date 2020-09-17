<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title>Sarafan</v-toolbar-title>
      <v-spacer></v-spacer>
      {{profile.name}}
      <v-btn icon href="/logout">
        <v-icon>{{ logout }}</v-icon>
      </v-btn>
    </v-app-bar>
    <v-content>
      <div v-if="!profile">Необходимо авторизоваться через
        <a href="/login">Google</a>
      </div>
      <div>
        <messages-list :messages="messages"/>
      </div>
    </v-content>
  </v-app>
</template>

<script>
  import MessagesList from 'components/messages/MessageList.vue'
  import { mdiExitToApp } from '@mdi/js'
  export default {
    components: {
      MessagesList
    },
    data () {
      return {
        messages: frontendData.messages,
        profile: frontendData.profile,
        logout: mdiExitToApp
      }
    }
  }
</script>

<style>

</style>