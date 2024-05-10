<template>
  <v-container>
    <v-row class="mt-1">
      <v-col>
        <v-dialog
            v-model="addUsersDialog"
            scrollable
            max-width="600px"
        >
          <v-card>
            <v-card-title>Add users
              <v-spacer></v-spacer>
              <span class="text-decoration-underline">Geography class .2</span></v-card-title>
            <v-divider></v-divider>
            <v-card-text>

              <form @submit.prevent="addUserToUserGroup">
                <v-row>
                  <v-col
                      cols="8"
                  >
                    <v-text-field
                        v-model="userEmail"
                        label="User email"
                        type="email"
                        required
                    ></v-text-field>
                  </v-col>
                  <v-col align-self="center">
                    <v-btn
                        class="mr-4"
                        color="primary"
                        type="submit"
                        :disabled="userEmail === '' || userEmail === null || addUserToGroupLoading"
                        :loading="addUserToGroupLoading"
                    >
                      Add
                    </v-btn>
                    <v-btn @click="userEmail = ''">
                      Clear
                    </v-btn>

                  </v-col>

                </v-row>
              </form>
            </v-card-text>
            <v-divider></v-divider>
          </v-card>
        </v-dialog>
        <v-dialog
            v-model="createUserGroupDialog"
            persistent
            max-width="600px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="primary"
                   v-bind="attrs"
                   v-on="on"
            >
              Create
              <v-icon dense right color="white">mdi-plus</v-icon>
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="text-h5">Create new test group</span>
            </v-card-title>
            <v-card-text>
              <v-row>
                <v-col
                    cols="12"
                >
                  <v-text-field
                      label="Test group name"
                      required
                      v-model="newUserGroupName"
                  ></v-text-field>
                </v-col>

              </v-row>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                  color="blue darken-1"
                  text
                  @click="createUserGroupDialog = false"
              >
                Cancel
              </v-btn>
              <v-btn
                  color="blue darken-1"
                  text
                  @click="createUserGroup()"
              >
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <v-dialog
            v-model="updateUserGroupDialog"
            persistent
            max-width="600px"
        >
          <v-card>
            <v-card-title>
              <span class="text-h5">Update user group</span>
            </v-card-title>
            <v-card-text>
              <v-row>
                <v-col
                    cols="12"
                >
                  <v-text-field
                      label="Test group name"
                      required
                      v-model="updatedUserGroup.name"
                  ></v-text-field>
                </v-col>

              </v-row>
              <v-row justify="center">
                <v-simple-table v-if="updatedUserGroup.users.length > 0" height="300px">
                  <template v-slot:default>
                    <thead>
                    <tr>
                      <th class="text-left">
                        Test title
                      </th>
                      <th class="text-left">
                        Remove from test group
                      </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr
                        v-for="(user, index) in updatedUserGroup.users"
                        :key="user.id"
                    >
                      <td>{{ user.email }}</td>
                      <td class="text-right">
                        <v-btn icon color="error" @click="updatedUserGroup.users.splice(index, 1)">
                          <v-icon color="error">mdi-minus-circle</v-icon>
                        </v-btn>
                      </td>
                    </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-row>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                  color="blue darken-1"
                  text
                  @click="updateUserGroupDialog = false"
              >
                Cancel
              </v-btn>
              <v-btn
                  color="blue darken-1"
                  text
                  @click="updateUserGroup"
              >
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

      </v-col>
    </v-row>

    <v-row>
      <v-dialog
          v-model="deleteDialog"
          persistent
          max-width="290"
      >
        <v-card>
          <v-card-title class="text-h6">
            Are you sure?
          </v-card-title>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="red"
                text
                @click="deleteDialog = false"
            >
              Cancel
            </v-btn>
            <v-btn
                color="error"
                @click="deleteUserGroup"
            >
              Yes
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-col cols="4" v-for="(userGroupData) in this.data">
        <v-card
            outlined
        >
          <v-list-item three-line>
            <v-list-item-content>
              <v-list-item-title class="text-h6 mb-1">
                {{ userGroupData.name }}
                <v-btn
                    @click=" updatedUserGroup = userGroupData; updateUserGroupDialog = true"
                    icon
                >
                  <v-icon color="black">mdi-square-edit-outline</v-icon>
                </v-btn>
              </v-list-item-title>
              <v-list-item-subtitle class="text">Users: {{ userGroupData.users.length }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-card-actions>
            <v-row>
              <v-col>
                <v-btn
                    rounded
                    color="success"
                    @click="addUsersDialog = true; addUserToGroup.userGroupId = userGroupData.id"
                >
                  Add users
                  <v-icon right color="white">mdi-plus</v-icon>
                </v-btn>
              </v-col>
              <v-col cols="auto" align-self="end">
                <v-btn rounded @click="userGroupIdToDelete = userGroupData.id; deleteDialog = true" color="error">
                  <v-icon color="white">mdi-delete</v-icon>
                </v-btn>

              </v-col>
            </v-row>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
    <v-snackbar
        v-model="addedToGroupSuccess"
        :timeout="3000"
        centered
        top
        color="success"
        elevation="24"
        :multi-line="true"
    >
      {{ "User was added to the user group!" }}

      <template v-slot:action="{ attrs }">
        <v-btn
            text
            v-bind="attrs"
            @click="addedToGroupSuccess = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
    <v-snackbar
        v-model="addUserToGroupFailed"
        :timeout="3000"
        centered
        top
        color="error"
        elevation="24"
        :multi-line="true"
    >
      {{ addUserToGroupFailedMessage }}

      <template v-slot:action="{ attrs }">
        <v-btn
            text
            v-bind="attrs"
            @click="addUserToGroupFailed = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      userEmail: '',
      addUsersDialog: false,
      addUserToGroupLoading: false,
      addUserToGroup: {userGroupId: null, userId: null},
      addedToGroupSuccess: false,
      addUserToGroupFailed: false,
      addUserToGroupFailedMessage: null,
      updatedUserGroup: {users: []},
      updateUserGroupDialog: null,
      deleteDialog: false,
      createLoading: false,
      newUserGroupName: null,
      userGroupIdToDelete: null,
      createUserGroupDialog: false,
      data: [],
    };
  },
  methods: {
    fetchUserGroups() {
      console.log(this.$store)
      this.$store.dispatch("userGroup/getAllUserGroups").then(data => {
        console.log(data);
        this.data = data;
        this.loading = false
      });
    },
    createUserGroup() {
      console.log(this.$store)
      this.$store.dispatch("userGroup/createUserGroup", {name: this.newUserGroupName}).then(data => {
        console.log(data);
        this.createUserGroupDialog = false;
        this.createLoading = false;
        this.newUserGroupName = null;
        this.fetchUserGroups();
      })
    },
    updateUserGroup() {
      this.$store.dispatch("userGroup/updateUserGroup", this.updatedTestGroup).then(() => {
        this.fetchUserGroups();
      })
      this.updateUserGroupDialog = false;
    },
    deleteUserGroup() {
      console.log("delete by id: " + this.userGroupIdToDelete);
      this.$store.dispatch("userGroup/deleteUserGroup", this.userGroupIdToDelete).then(() => {
        this.fetchUserGroups();
      });
      this.deleteDialog = false
    },
    addUserToUserGroup() {
      this.addUserToGroupLoading = true;
      console.log(this.userEmail);
      this.$store.dispatch("user/getUserByEmail", this.userEmail).then((userDetails) => {
        this.addUserToGroup.userId = userDetails.id;
        console.log('Add users to the group')
        console.log(userDetails);
        console.log('UserGroupId: ' + this.addUserToGroup.userGroupId);
        this.$store.dispatch('userGroup/moveUsersToGroup', {
          userGroupId: this.addUserToGroup.userGroupId, userIds: [this.addUserToGroup.userId]
        }).then(() => {
              this.addedToGroupSuccess = true;
              this.addUserToGroupLoading = false;

              this.fetchUserGroups();
            }
        ).catch(() => {
          this.addUserToGroupFailedMessage = "Failed to add user to the group!";
          this.addUserToGroupFailed = true;
          this.addUserToGroupLoading = false;
        });
      }).catch(error => {
        console.log(error);
        console.log(error.response)
        this.addUserToGroupFailedMessage = "Didn't find an user with the provided email!";
        this.addUserToGroupFailed = true;
        this.addUserToGroupLoading = false;
      });

    }
  },
  mounted() {
    this.fetchUserGroups();
  }
};
</script>

<style scoped>
/* Add your custom styles here */
</style>
