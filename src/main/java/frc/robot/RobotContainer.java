    //elevator commands, half and full and down
    private final ElevatorUpCommand m_elevHalfUpCommand = new ElevatorUpCommand(m_elevatorSubsytem, true);
    private final ElevatorUpCommand m_elevFullUpCommand = new ElevatorUpCommand(m_elevatorSubsytem, false);
    private final ElevatorDownCommand m_elevDownCommand = new ElevatorDownCommand(m_elevatorSubsytem);
  
    //Auto Sequences
    private final AutonomousGroup m_autonomousGroup = new AutonomousGroup(m_drivetrainSubsystem,  m_intakeSubsystem);
  
    // JOYSTICKS
    public static Joystick rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK_CHANNEL);
    public static Joystick leftJoystick = new Joystick(Constants.LEFT_JOYSTICK_CHANNEL);
  
  
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
      // Configure the button bindings
      configureButtonBindings();
      
    }
    
    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
      Button intakeButton = new JoystickButton(rightJoystick, Constants.R_SPIN_INTAKE_FORWARD_BUTTON);
      Button elevHalfUpButton = new JoystickButton(rightJoystick, Constants.ELEVATOR_HALF_UP_BUTTON);
      Button elevFullUpButton = new JoystickButton(rightJoystick, Constants.ELEVATOR_FULL_UP_BUTTON);
      Button elevDownButton = new JoystickButton(rightJoystick, Constants.ELEVATOR_DOWN_BUTTON);
      //free spins when intakeButton is held, corrects with a slower speed when intakeButton is released
      intakeButton.whenHeld(m_intakeCommandFreeSpin);
      intakeButton.whenReleased(m_intakeCommand_doCorrection);
      //does elevator commands depending on what button pressed
      elevHalfUpButton.whenPressed(m_elevHalfUpCommand);
      elevFullUpButton.whenPressed(m_elevFullUpCommand);
      elevDownButton.whenPressed(m_elevDownCommand);
    }
  
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
      // An ExampleCommand will run in autonomous
  
      m_autoChooser = new SendableChooser<>();
      m_autoChooser.addOption("Drive Straight", m_autonomousGroup);
      SmartDashboard.putData("AutoMode", m_autoChooser);
      
      // return m_autoChooser.getSelected();
      return m_autonomousGroup;
    }
  }