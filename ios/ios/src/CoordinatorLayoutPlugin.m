//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: D:\Java\git\core-ios-widgets\IOSCoordinatorLayoutPlugin\src\main\java\com\ashera\coordinatorlayout\CoordinatorLayoutPlugin.java
//

#include "CoordinatorLayoutImpl.h"
#include "CoordinatorLayoutPlugin.h"
#include "J2ObjC_source.h"
#include "WidgetFactory.h"


@implementation ASCoordinatorLayoutPlugin

J2OBJC_IGNORE_DESIGNATED_BEGIN
- (instancetype)init {
  ASCoordinatorLayoutPlugin_init(self);
  return self;
}
J2OBJC_IGNORE_DESIGNATED_END

+ (void)initPlugin {
  ASCoordinatorLayoutPlugin_initPlugin();
}

+ (const J2ObjcClassInfo *)__metadata {
  static J2ObjcMethodInfo methods[] = {
    { NULL, NULL, 0x1, -1, -1, -1, -1, -1, -1 },
    { NULL, "V", 0x9, -1, -1, -1, -1, -1, -1 },
  };
  #pragma clang diagnostic push
  #pragma clang diagnostic ignored "-Wobjc-multiple-method-names"
  #pragma clang diagnostic ignored "-Wundeclared-selector"
  methods[0].selector = @selector(init);
  methods[1].selector = @selector(initPlugin);
  #pragma clang diagnostic pop
  static const J2ObjcClassInfo _ASCoordinatorLayoutPlugin = { "CoordinatorLayoutPlugin", "com.ashera.coordinatorlayout", NULL, methods, NULL, 7, 0x1, 2, 0, -1, -1, -1, -1, -1 };
  return &_ASCoordinatorLayoutPlugin;
}

@end

void ASCoordinatorLayoutPlugin_init(ASCoordinatorLayoutPlugin *self) {
  NSObject_init(self);
}

ASCoordinatorLayoutPlugin *new_ASCoordinatorLayoutPlugin_init() {
  J2OBJC_NEW_IMPL(ASCoordinatorLayoutPlugin, init)
}

ASCoordinatorLayoutPlugin *create_ASCoordinatorLayoutPlugin_init() {
  J2OBJC_CREATE_IMPL(ASCoordinatorLayoutPlugin, init)
}

void ASCoordinatorLayoutPlugin_initPlugin() {
  ASCoordinatorLayoutPlugin_initialize();
  ASWidgetFactory_register__WithASIWidget_(new_ASCoordinatorLayoutImpl_init());
}

J2OBJC_CLASS_TYPE_LITERAL_SOURCE(ASCoordinatorLayoutPlugin)
