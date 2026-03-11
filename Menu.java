import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner in = new Scanner(System.in);
    private static String way;
    private static String name;
    private static int lengthMs;
    private static double volume;
    private static int lowFrequency;
    private static int highFrequency;
    private static int resonance;
    private static int punch;
    private static int bassLevel;
    private static boolean closed;
    private static int tailLength;
    
    private static DawInOut manager = new DawInOut();
    private static Daw daw = new Daw();
    
    public static void main(String[] args) {
        System.out.println("=== DAW ===\n");
        
        while (true) {
            try {
                System.out.println("\n======== ОСНОВНОЕ МЕНЮ ========");
                System.out.println("1. Создать/удалить сэмплы");
                System.out.println("2. Работать с Kick");
                System.out.println("3. Работать с Snare");
                System.out.println("4. Работать с Hi-Hat");
                System.out.println("5. Показать информацию обо всех сэмплах");
                System.out.println("6. Сохранить проект");
                System.out.println("7. Открыть проект");
                System.out.println("8. Выход");
                System.out.print("\nВыберите пункт меню: ");

                int choice = in.nextInt();
                in.nextLine();
                
                switch (choice) {
                    case 1:
                        createSamplesMenu();
                        break;
                    case 2:
                        System.out.println("Введите название сэмпла");
                        name = in.nextLine();
                        if (daw.findSample(name, "KICK",true)) {
                            kickMenu();
                        } else {
                            System.out.println("Такого сэмпла не существует");
                        }
                        break;
                    case 3:
                        System.out.println("Введите название сэмпла");
                        name = in.nextLine();
                        if (daw.findSample(name, "SNARE",true)) {
                            snareMenu();
                        } else {
                            System.out.println("Такого сэмпла не существует");
                        }
                        break;
                    case 4:
                        System.out.println("Введите название сэмпла");
                        name = in.nextLine();
                        if (daw.findSample(name, "HAT",true)) {
                            hatMenu();
                        } else {
                            System.out.println("Такого сэмпла не существует");
                        }
                        break;
                    case 5:
                        daw.printAllInfo();
                        break;
                    case 6:
                        System.out.println("Введите директорию для сохранения: ");
                        way = in.nextLine();
                        manager.saveProject(daw, way);
                        break;
                    case 7:
                        System.out.println("Введите директорию для открытия: ");
                        way = in.nextLine();
                        daw = manager.loadProject(way);
                        break;
                    case 8:
                        return;
                    default:
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число для выбора пункта меню!");
                in.nextLine();
            }
        }
    }
    
    private static void createSamplesMenu() {
        while (true) {
            try {
                System.out.println("\n=== СОЗДАНИЕ СЭМПЛОВ ===");
                System.out.println("1. Создать Kick");
                System.out.println("2. Создать Snare");
                System.out.println("3. Создать Hi-Hat");
                System.out.println("4. Создать сэмплы по умолчанию");
                System.out.println("5. Удалить сэмпл");
                System.out.println("6. Назад в основное меню");
                System.out.print("\nВыберите тип сэмпла: ");
                
                int choice = in.nextInt();
                in.nextLine();
                
                switch (choice) {
                    case 1:
                        System.out.println("Введите название Kick'a");
                        name = in.nextLine();
                        if (daw.findSample(name, "KICK",true) == true) {
                            System.out.println("Сэмпл с таким именем уже существует");
                            break;
                        }
                        while (true) {
                            try {
                                System.out.println("Введите длину (в милисекундах, 0-10000)");
                                lengthMs = in.nextInt();
                                if (lengthMs >= 0 && lengthMs <= 10000) {
                                    break;
                                } else {
                                    System.out.println("Ошибка: длина должна быть в диапазоне [0; 10000]!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите целое число для длины!");
                                in.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Введите громкость (от 0 до 1)");
                                volume = in.nextDouble();
                                if (volume >= 0.0 && volume <= 1.0) {
                                    break;
                                } else {
                                    System.out.println("Ошибка: громкость должна быть в диапазоне [0; 1]!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите число для громкости!");
                                in.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Введите нижнюю границу частотного диапазона (0-20000)");
                                lowFrequency = in.nextInt();
                                if (lowFrequency < 0 || lowFrequency > 20000) {
                                    System.out.println("Ошибка: частота должна быть в диапазоне [0; 20000]!");
                                    continue;
                                }
                                
                                System.out.println("Введите верхнюю границу частотного диапазона (0-20000)");
                                highFrequency = in.nextInt();
                                if (highFrequency < 0 || highFrequency > 20000) {
                                    System.out.println("Ошибка: частота должна быть в диапазоне [0; 20000]!");
                                    continue;
                                }
                                
                                if (lowFrequency > highFrequency) {
                                    System.out.println("Ошибка: нижняя граница не может быть больше верхней!");
                                    continue;
                                }
                                
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите целые числа для частот!");
                                in.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Введите уровень басса (0-100)");
                                bassLevel = in.nextInt();
                                if (bassLevel >= 0 && bassLevel <= 100) {
                                    break;
                                } else {
                                    System.out.println("Ошибка: уровень басса должен быть в диапазоне [0; 100]!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите целое число для уровня басса!");
                                in.nextLine();
                            }
                        }
                        
                        daw.CreateSample(new Kick(name, lengthMs, volume, lowFrequency, highFrequency, bassLevel));
                        break;
                        
                    case 2:
                        System.out.println("Введите название Snare'a");
                        name = in.nextLine();
                        if (daw.findSample(name, "SNARE",true) == true) {
                            System.out.println("Сэмпл с таким именем уже существует");
                            break;
                        }
                        while (true) {
                            try {
                                System.out.println("Введите длину (в милисекундах, 0-10000)");
                                lengthMs = in.nextInt();
                                if (lengthMs >= 0 && lengthMs <= 10000) {
                                    break;
                                } else {
                                    System.out.println("Ошибка: длина должна быть в диапазоне [0; 10000]!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите целое число для длины!");
                                in.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Введите громкость (от 0 до 1)");
                                volume = in.nextDouble();
                                if (volume >= 0.0 && volume <= 1.0) {
                                    break;
                                } else {
                                    System.out.println("Ошибка: громкость должна быть в диапазоне [0; 1]!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите число для громкости!");
                                in.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Введите нижнюю границу частотного диапазона (0-20000)");
                                lowFrequency = in.nextInt();
                                if (lowFrequency < 0 || lowFrequency > 20000) {
                                    System.out.println("Ошибка: частота должна быть в диапазоне [0; 20000]!");
                                    continue;
                                }
                                System.out.println("Введите верхнюю границу частотного диапазона (0-20000)");
                                highFrequency = in.nextInt();
                                if (highFrequency < 0 || highFrequency > 20000) {
                                    System.out.println("Ошибка: частота должна быть в диапазоне [0; 20000]!");
                                    continue;
                                }
                                if (lowFrequency > highFrequency) {
                                    System.out.println("Ошибка: нижняя граница не может быть больше верхней!");
                                    continue;
                                }
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите целые числа для частот!");
                                in.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Введите уровень резонанса (0-100)");
                                resonance = in.nextInt();
                                if (resonance >= 0 && resonance <= 100) {
                                    break;
                                } else {
                                    System.out.println("Ошибка: уровень резонанса должен быть в диапазоне [0; 100]!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите целое число для резонанса!");
                                in.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Введите уровень панча (0-100)");
                                punch = in.nextInt();
                                if (punch >= 0 && punch <= 100) {
                                    break;
                                } else {
                                    System.out.println("Ошибка: уровень панча должен быть в диапазоне [0; 100]!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите целое число для панча!");
                                in.nextLine();
                            }
                        }
                        
                        daw.CreateSample(new Snare(name, lengthMs, volume, lowFrequency, highFrequency, resonance, punch));
                        break;
                        
                    case 3:
                        System.out.println("Введите название Hat'a");
                        name = in.nextLine();
                        if (daw.findSample(name, "HAT",true) == true) {
                            System.out.println("Сэмпл с таким именем уже существует");
                            break;
                        }
                        while (true) {
                            try {
                                System.out.println("Введите длину (в милисекундах, 0-10000)");
                                lengthMs = in.nextInt();
                                if (lengthMs >= 0 && lengthMs <= 10000) {
                                    break;
                                } else {
                                    System.out.println("Ошибка: длина должна быть в диапазоне [0; 10000]!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите целое число для длины!");
                                in.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Введите громкость (от 0 до 1)");
                                volume = in.nextDouble();
                                if (volume >= 0.0 && volume <= 1.0) {
                                    break;
                                } else {
                                    System.out.println("Ошибка: громкость должна быть в диапазоне [0; 1]!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите число для громкости!");
                                in.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Введите нижнюю границу частотного диапазона (0-20000)");
                                lowFrequency = in.nextInt();
                                if (lowFrequency < 0 || lowFrequency > 20000) {
                                    System.out.println("Ошибка: частота должна быть в диапазоне [0; 20000]!");
                                    continue;
                                }
                                
                                System.out.println("Введите верхнюю границу частотного диапазона (0-20000)");
                                highFrequency = in.nextInt();
                                if (highFrequency < 0 || highFrequency > 20000) {
                                    System.out.println("Ошибка: частота должна быть в диапазоне [0; 20000]!");
                                    continue;
                                }
                                
                                if (lowFrequency > highFrequency) {
                                    System.out.println("Ошибка: нижняя граница не может быть больше верхней!");
                                    continue;
                                }
                                
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите целые числа для частот!");
                                in.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Введите длину хвоста (0-100)");
                                tailLength = in.nextInt();
                                if (tailLength >= 0 && tailLength <= 100) {
                                    break;
                                } else {
                                    System.out.println("Ошибка: длина хвоста должна быть в диапазоне [0; 100]!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите целое число для длины хвоста!");
                                in.nextLine();
                            }
                        }
                        while (true) {
                            try {
                                System.out.println("Укажите тип:\n1) Открытый (Open-Hat)\n2) Закрытый (Closed-Hat)");
                                int hatType = in.nextInt();
                                if (hatType == 1 || hatType == 2) {
                                    closed = (hatType == 2);
                                    break;
                                } else {
                                    System.out.println("Ошибка: введите 1 или 2 для типа Hat!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите 1 или 2 для типа Hat!");
                                in.nextLine();
                            }
                        }
                        
                        daw.CreateSample(new Hat(name, lengthMs, volume, lowFrequency, highFrequency, tailLength, closed));
                        break;
                        
                    case 4:
                        daw.CreateSample(new Kick("Deep Kick", 300, 0.9, 60, 200, 70));
                        daw.CreateSample(new Snare("Snappy Snare", 400, 0.85, 150, 5000, 60, 75));
                        daw.CreateSample(new Hat("Hi-Hat Classic", 500, 0.8, 1000, 10000, 50, true));
                        break;
                    case 5:
                        System.out.println("Введите имя сэмпла");
                        name = in.nextLine();
                        while (true) {
                            try {
                                System.out.println("Выберите тип сэмпла: \n1)Kick \n2)Snare \n3)Hat");
                                choice = in.nextInt();
                                if (choice == 1) {
                                    if (daw.findSample(name, "KICK",false)) {
                                        System.out.println("Сэмпл с именем " + name + "успешно удаленн!");
                                        break;
                                    } else {
                                        System.out.println("Такого сэмпла не существует");
                                        break;
                                    }
                                    
                                } else if (choice == 2) {
                                    if (daw.findSample(name, "SNARE",false)) {
                                        System.out.println("Сэмпл с именем " + name + "успешно удаленн!");
                                        break;
                                    } else {
                                        System.out.println("Такого сэмпла не существует");
                                        break;
                                    }
                                } else if (choice == 3) {
                                    if (daw.findSample(name, "HAT",false)) {
                                        System.out.println("Сэмпл с именем " + name + "успешно удаленн!");
                                        break;
                                    } else {
                                        System.out.println("Такого сэмпла не существует");
                                        break;
                                    }
                                } else System.out.println("Нет такого пункта меню");
                            } catch (InputMismatchException e) {
                                System.out.println("Ошибка: введите целое число для выбора!");
                                in.nextLine();
                            }
                        }
                        
                    case 6:
                        return;
                    default:
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число для выбора пункта меню!");
                in.nextLine();
            }
        }
    }
    
    private static void kickMenu() {
        while (true) {
            try {
                System.out.println("\n=== МЕНЮ KICK ===");
                System.out.println("1. Увеличить бас");
                System.out.println("2. Уменьшить бас");
                System.out.println("3. Применить эффект");
                System.out.println("4. Показать информацию");
                System.out.println("5. Послушать");
                System.out.println("6. Назад в основное меню");
                System.out.print("\nВыберите действие: ");
                
                int choice = in.nextInt();
                in.nextLine();
                
                switch (choice) {
                    case 1:
                        daw.increaseKickBass();
                        break;
                    case 2:
                        daw.decreaseKickBass();
                        break;
                    case 3:
                        daw.applyEffect("KICK");
                        break;
                    case 4:
                        daw.printInfo("KICK");
                        break;
                    case 5:
                        daw.playSample("KICK");
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число для выбора пункта меню!");
                in.nextLine();
            }
        }
    }
    
    private static void snareMenu() {
        while (true) {
            try {
                System.out.println("\n=== МЕНЮ SNARE ===");
                System.out.println("1. Увеличить панч");
                System.out.println("2. Уменьшить панч");
                System.out.println("3. Применить эффект");
                System.out.println("4. Показать информацию");
                System.out.println("5. Послушать");
                System.out.println("6. Назад в основное меню");
                System.out.print("\nВыберите действие: ");
                
                int choice = in.nextInt();
                in.nextLine();
                
                switch (choice) {
                    case 1:
                        daw.increaseSnarePunch();
                        break;
                    case 2:
                        daw.decreaseSnarePunch();
                        break;
                    case 3:
                        daw.applyEffect("SNARE");
                        break;
                    case 4:
                        daw.printInfo("SNARE");
                        break;
                    case 5:
                        daw.playSample("SNARE");
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число для выбора пункта меню!");
                in.nextLine();
            }
        }
    }
    
    private static void hatMenu() {
        while (true) {
            try {
                System.out.println("\n=== МЕНЮ HI-HAT ===");
                System.out.println("1. Увеличить хвост");
                System.out.println("2. Уменьшить хвост");
                System.out.println("3. Переключить открыт/закрыт");
                System.out.println("4. Применить эффект");
                System.out.println("5. Показать информацию");
                System.out.println("6. Послушать");
                System.out.println("7. Назад в основное меню");
                System.out.print("\nВыберите действие: ");
                
                int choice = in.nextInt();
                in.nextLine();
                
                switch (choice) {
                    case 1:
                        daw.increaseHatTail();
                        break;
                    case 2:
                        daw.decreaseHatTail();
                        break;
                    case 3:
                        daw.toggleHatClosed();
                        break;
                    case 4:
                        daw.applyEffect("HAT");
                        break;
                    case 5:
                        daw.printInfo("HAT");
                        break;
                    case 6:
                        daw.playSample("HAT");
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число для выбора пункта меню!");
                in.nextLine();
            }
        }
    }
}