public abstract class Personnel {
    protected String name;
    protected String surname;
    protected String registrationNumber;
    protected String position;
    protected int yearOfStart;
    int[] hoursWorked;

    public Personnel(String name, String surname, String registerNumber, String position, int yearOfStart) {
        this.name = name;
        this.surname = surname;
        this.registrationNumber = registerNumber;
        this.position = position;
        this.yearOfStart = yearOfStart;
    }

         String getName() {
            return name;
        }

        public void setName (String name){
            this.name = name;
        }

        public String getSurname () {
            return surname;
        }

        public void setSurname (String surname){
            this.surname = surname;
        }

        public String getRegistrationNumber () {
            return registrationNumber;
        }


        public void setRegistrationNumber (String registerNumber){
            this.registrationNumber= registerNumber;
        }

        public String getPosition () {
            return position;
        }

        public void setPosition (String position){
            this.position = position;
        }

        public int getYearOfStart () {
            return yearOfStart;
        }

        public void setYearOfStart ( int yearOfStart){
            this.yearOfStart = yearOfStart;
        }

        public abstract String calculateSalary(int[] hoursWorked);

        public void setHoursWorked(int[] hoursWorked) {
            this.hoursWorked = hoursWorked;
    }
}
