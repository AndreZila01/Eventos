package pt.iade.andrecustodio.casaapp.Modelos;

public class ClassLogin {


        private int Id;
        private String Username;
        private String Password;

        public ClassLogin() {
            this(0, "", "");
        }

        public ClassLogin(int id, String username, String password) {
            this.Id = id;
            this.Username = username;
            this.Password = password;
        }

        public void setId(int id) {
            this.Id = id;
        }

        public void setUsername(String username) {
            this.Username = username;
        }

        public void setPassword(String password) {
            this.Password = password;
        }

        public int getId() {
            return this.Id;
        }

        public String getUsername() {
            return this.Username;
        }

        public String getPassword() {
            return this.Password;
        }


}
